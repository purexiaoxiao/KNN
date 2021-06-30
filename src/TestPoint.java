import com.xxpure.kmeans.KMeans;
import com.xxpure.kmeans.KMeansInterface;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class TestPoint implements KMeansInterface<Point> {
	private int cnt = 0;

	public static void main(String[] args) {
		KMeans<Point> means = new KMeans<>(PointFactory.getInitialData(), 10);
		TestPoint testPoint = new TestPoint();
		while (true) {
			try {
				means.nextSample(testPoint);
				System.out.println(means.getSamples());
			} catch (Exception e) {
				break;
			}
		}
	}

	@Override
	public boolean isOver() {
		return cnt++ > 100;
	}

	@Override
	public Point getAverageSample(ArrayList<Point> points) {
		int x = 0, y = 0;
		for (var point : points) {
			x += point.getX();
			y += point.getY();
		}
		return new Point(x / points.size(), y / points.size());
	}

	@Override
	public int getDistance(Point elements, Point point) {
		return (elements.x - point.x) * (elements.x - point.x) + (elements.y - point.y) * (elements.y - point.y);
	}

	private static class PointFactory {
		public static ArrayList<Point> getInitialData() {
			var initialData = new ArrayList<Point>();
			var random = new Random();
			for (int i = 1; i <= 1000; i++) {
				initialData.add(new Point(random.nextInt(100), random.nextInt(100)));
			}
			return initialData;
		}
	}
}


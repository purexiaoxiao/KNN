package com.xxpure.kmeans;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;

public class KMeans<E> {
	private final ArrayList<E> initialData;//初始数据
	private final ArrayList<E> samples;//样本
	private final ArrayList<E>[] C;

	public KMeans(ArrayList<E> initialData, int k) {
		C = new ArrayList[k];
		this.initialData = initialData;
		samples = getInitialSample(k);
		for (int i = 0; i < samples.size(); i++)
			C[i] = new ArrayList<>();
	}

	public ArrayList<E> getSamples() {
		return samples;
	}

	public ArrayList<E>[] getC() {
		return C;
	}

	private void clearC() {
		for (int i = 0; i < samples.size(); i++) {
			C[i].clear();
		}
	}

	private ArrayList<E> getInitialSample(int k) {
		var sampleIndex = new HashSet<Integer>();
		var samples = new ArrayList<E>();
		var randomSample = new Random();
		int res = 0;
		while (samples.size() < k) {
			while (sampleIndex.contains(res))
				res = randomSample.nextInt(k);

			samples.add(initialData.get(res));
			sampleIndex.add(res);
		}
		return samples;
	}

	public ArrayList<E> nextSample(KMeansInterface<E> compare) throws Exception {

		if (compare.isOver())
			throw new Exception("this samples is the best");
		clearC();
		for (var elements : initialData) {
			int minIndex = 0;
			int minDistance = Integer.MAX_VALUE;
			for (int i = 0; i < samples.size(); i++) {
				int elementDistance = compare.getDistance(elements, samples.get(i));
				if (elementDistance < minDistance) {
					minDistance = elementDistance;
					minIndex = i;
				}
				C[minIndex].add(elements);
			}
		}
		for (int i = 0; i < samples.size(); i++) {
			samples.set(i, compare.getAverageSample(C[i]));
		}
		return samples;
	}
}

package com.xxpure.kmeans;

import java.util.ArrayList;

public interface KMeansInterface<E> {


	boolean isOver();

	E getAverageSample(ArrayList<E> es);


	int getDistance(E elements, E e);
}

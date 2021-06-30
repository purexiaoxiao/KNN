package com.xxpure.kmeans;

import java.util.ArrayList;

public interface KMeansInterface<E> {


	/**
	 * KNN结束判断方法
	 *
	 * @return 返回是否结束的状态
	 */
	boolean isOver();

	/**
	 * @param es 带更新的一类
	 * @return 放回均值向量
	 */
	E getAverageSample(ArrayList<E> es);


	/**
	 * 返回两个对象之间的距离
	 */
	int getDistance(E elements, E e);
}

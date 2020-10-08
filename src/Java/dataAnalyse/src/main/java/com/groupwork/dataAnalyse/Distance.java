package com.groupwork.dataAnalyse;

public class Distance {
	public static Double cal(Point a, Point b, Double[] weight) {
		Double dis = 0.0;
		for(int i = 0;i < a.getVal().length;i++) {
			dis += weight[i] * (a.getVal()[i] - b.getVal()[i]) * (a.getVal()[i] - b.getVal()[i]);
		}
		return dis;
	}
}

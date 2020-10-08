package com.groupwork.dataAnalyse;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class Kmeans {
	private String url;
	private Integer k;
	
	private static final Integer iterMax = 100000;
	private Integer iterNow = 0;
	private static final Double disDiff = 0.01;
	
	private Integer amount = 0;
	
	private List<Point> pointSet = null;
	
	private Integer dimension;
	private Double[] weight;
	
	private Boolean stadardized;
	private Double[] means;
	private Double[] variances;

	public Kmeans(Integer k, String url, Integer dimension, Double[] weight, Boolean stadardized) {
		super();
		this.k = k;
		this.url = url;
		this.dimension = dimension;
		this.weight = weight;
		this.pointSet = new ArrayList<Point>();
		this.stadardized = stadardized;
	}
	
	private void stadardize() {
		means = new Double[dimension];
		variances = new Double[dimension];
		for(int i = 0;i < dimension;i++) {
			Double d = 0.0;
			means[i] = d;
		}
		for(int i = 0;i < dimension;i++) {
			Double d = 0.0;
			variances[i] = d;
		}
		for(Point p: pointSet) {
			for(int i = 0;i < dimension;i++) {
				means[i] += p.getVal()[i];
				variances[i] += p.getVal()[i] * p.getVal()[i];
			}
		}
		for(int i = 0;i < dimension;i++) {
			Integer n = pointSet.size();
			means[i] /= n;
			variances[i] = (variances[i] - n * means[i] * means[i]) / (n - 1);
		}
		for(int i = 0;i < pointSet.size();i++) {
			for(int j = 0;j < dimension;j++) {
				pointSet.get(i).getVal()[j] = (pointSet.get(i).getVal()[j] - means[j]) / Math.sqrt(variances[j]);
			}
		}
	}
	
	private void readData() { 
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(this.url));
			String line = br.readLine();
			while(line != null) {
				String[] info = line.split(",");
				Point p = new Point();
				p.setId(amount);
				amount++;
				Double[] infod = new Double[info.length - 1]; 
				for(int i = 1;i < info.length; i++) {
					try {
						infod[i - 1] = Double.parseDouble(info[i]);
					}catch(Exception e) {
						infod[i - 1] = 0.0;
					}
				}
				p.setVal(infod);
				p.setId(Integer.parseInt(info[0]));
				pointSet.add(p);
				line = br.readLine();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		if(this.stadardized) {
			this.stadardize();
		}
	}
	
	private Set<Cluster> chooseCenter(){
		Set<Cluster> clusterSet = new HashSet<Cluster>();
		Random random = new Random();
		for(int i = 0;i < k;) {
			Point p = pointSet.get(random.nextInt(pointSet.size()));
			Boolean flag = true;
			for(Cluster c: clusterSet) {
				if(c.getCenter().equals(p))
					flag = false;
			}
			if(flag) {
				Cluster c = new Cluster(i, p);
				clusterSet.add(c);
				i++;
			}
		}
		return clusterSet;
	}

	private void cluster(Set<Cluster> clusterSet) {
		for(Point p: pointSet) {
			Double minDis = 0.0 + Integer.MAX_VALUE;
			for(Cluster c: clusterSet) {
				Double tmpDis = (Double)Math.min(Distance.cal(p, c.getCenter(), weight), minDis);
				if(tmpDis < minDis) {
					minDis = tmpDis;
					p.setClusterId(c.getId());
					p.setDis(minDis);
				}
			}
		}
		for(Cluster c: clusterSet) {
			c.getMembers().clear();
			for(Point p: pointSet) {
				if(p.getClusterId() == c.getId()) {
					c.add(p);
				}
			}
		}
	}
	
	private Boolean calCenter(Set<Cluster> clusterSet) {
		Boolean needIter = false;
		for(Cluster c: clusterSet) {
			List<Point> pointList = c.getMembers();
			Double[] sumAll = new Double[dimension];
			
			for(int i = 0;i < dimension;i++) {
				Double d = 0.0;
				sumAll[i] = d;
			}
			
			for(int i = 0;i < dimension; i++) {
				for(int j = 0;j < pointList.size();j++) {
					sumAll[i] += pointList.get(j).getVal()[i];
				}
			}
			for(int i = 0;i < dimension;i++) {
				sumAll[i] = (Double)sumAll[i]/pointList.size();
			}
			if(Distance.cal(c.getCenter(), new Point(sumAll), weight) > disDiff) {
				needIter = true;
			}
			c.setCenter(new Point(sumAll));
		}
		return needIter;
	}
	public Set<Cluster> run(){	
		this.readData();
		Set<Cluster> clusterSet = this.chooseCenter();
		Boolean needIter = true;
		while(needIter) {
			this.cluster(clusterSet);
			needIter = this.calCenter(clusterSet);
			this.iterNow++;
		}
		if(this.stadardized) {
			for(Cluster c: clusterSet) {
				for(int i = 0;i < c.getMembers().size(); i++) {
					Point p = c.getMembers().get(i);
					for(int j = 0;j < dimension; j++) {
						p.getVal()[j] = p.getVal()[j] * Math.sqrt(variances[j]) + means[j];
					}
				}
				Point cen = c.getCenter();
				for(int i = 0;i < dimension;i++)
					cen.getVal()[i] = cen.getVal()[i] * Math.sqrt(variances[i]) + means[i];
			}
		}
		return clusterSet;
	}
	public Integer getIterTime() {
		return this.iterNow;
	}
	public static void main(String[] args) throws IOException {
		String url = "d://calData.csv";
		Integer dimension = 6;
		Integer k = 5;
		Double[] weight = {1.0, 1.0, 1.0, 1.0, 1.0, 1.0};
		Kmeans kmeans = new Kmeans(k, url, dimension, weight, true);
		
		Set<Cluster> set = kmeans.run();
		BufferedWriter bw = new BufferedWriter(new FileWriter("d://output//cluster.csv"));
		for(Cluster c: set) {
			bw.write(c.toString());
			bw.newLine();
		}
		bw.close();
	}
}

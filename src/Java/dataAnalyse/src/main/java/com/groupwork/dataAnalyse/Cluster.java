package com.groupwork.dataAnalyse;

import java.util.ArrayList;
import java.util.List;

public class Cluster {
	private Integer id;
	private Point center;
	private List<Point> members = new ArrayList<Point>();
	
	public Cluster(Integer id, Point center, List<Point> members) {
		super();
		this.id = id;
		this.center = center;
		this.members = members;
	}
	@Override
	public String toString() {
		String str = "";
		str += id + "," + center.getVal().length;
		for(Double d: center.getVal()) {
			str += "," + d;
		}
		str += "," + members.size();
		for(Point p: members) {
			str += "," + p.toString();
		}
		return str;
	}
	public Cluster(Integer id, Point center) {
		super();
		this.id = id;
		this.center = center;
	}
	public Cluster() {
		super();
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Point getCenter() {
		return center;
	}
	public void setCenter(Point center) {
		this.center = center;
	}
	public List<Point> getMembers() {
		return members;
	}
	public void setMembers(List<Point> member) {
		this.members = member;
	}
	
	public void add(Point p) {
		members.add(p);	
	}
	
}

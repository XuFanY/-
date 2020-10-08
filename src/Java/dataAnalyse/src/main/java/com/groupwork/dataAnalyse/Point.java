package com.groupwork.dataAnalyse;

public class Point {
	private Double[] val;
	private Integer id;
	private Integer clusterId;
	private Double dis;

	@Override
	public boolean equals(Object obj) {
		if(obj == null || getClass() != obj.getClass())
			return false;
		Point p = (Point)obj;
		if(p.val.length != this.val.length)
			return false;
		
		for(int i = 0; i < this.val.length; i++) {
			if(Double.compare(p.val[i], this.val[i]) != 0)
				return false;
		}
		return true;
	}
	public Double[] getVal() {
		return val;
	}
	public void setVal(Double[] val) {
		this.val = val;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getClusterId() {
		return clusterId;
	}
	public void setClusterId(Integer clusterId) {
		this.clusterId = clusterId;
	}
	public Double getDis() {
		return dis;
	}
	public void setDis(Double dis) {
		this.dis = dis;
	}
	public Point(Double[] val, Integer id, Integer clusterId, Double dis) {
		super();
		this.val = val;
		this.id = id;
		this.clusterId = clusterId;
		this.dis = dis;
	}
	public Point() {
		super();
	}
	public Point(Double[] val) {
		this.val = val;
	}
	@Override
	public String toString() {
		String str = "";
		if(id != null)
			str += id;
		for(Double v: val) {
			str += "," + v;
		}
		return str;
	}
	
}

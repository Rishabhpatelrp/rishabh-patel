package com.locus.sh.locationsimulator.bean;

public class LocationPolyLineBean {
	private String points;

	public LocationPolyLineBean() {
		super();
	}

	public LocationPolyLineBean(String points) {
		super();
		this.points = points;
	}

	public String getPoints() {
		return points;
	}

	public void setPoints(String points) {
		this.points = points;
	}

	@Override
	public String toString() {
		return "LocationPolyLine [points=" + points + "]";
	}

}

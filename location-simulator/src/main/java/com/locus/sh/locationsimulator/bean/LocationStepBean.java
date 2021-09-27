package com.locus.sh.locationsimulator.bean;

public class LocationStepBean {
	private LocationDistanceBean distance;
	private LocationPointBean end_location;
	private LocationPointBean start_location;
	private LocationPolyLineBean polyline;

	public LocationStepBean() {
		super();
	}

	public LocationStepBean(LocationDistanceBean distance, LocationPointBean end_location, LocationPointBean start_location,
			LocationPolyLineBean polyline) {
		super();
		this.distance = distance;
		this.end_location = end_location;
		this.start_location = start_location;
		this.polyline = polyline;
	}

	public LocationDistanceBean getDistance() {
		return distance;
	}

	public void setDistance(LocationDistanceBean distance) {
		this.distance = distance;
	}

	public LocationPointBean getEnd_location() {
		return end_location;
	}

	public void setEnd_location(LocationPointBean end_location) {
		this.end_location = end_location;
	}

	public LocationPointBean getStart_location() {
		return start_location;
	}

	public void setStart_location(LocationPointBean start_location) {
		this.start_location = start_location;
	}

	public LocationPolyLineBean getPolyline() {
		return polyline;
	}

	public void setPolyline(LocationPolyLineBean polyline) {
		this.polyline = polyline;
	}

	@Override
	public String toString() {
		return "LocationStep [distance=" + distance + ", end_location=" + end_location + ", start_location="
				+ start_location + ", polyline=" + polyline + "]";
	}

}

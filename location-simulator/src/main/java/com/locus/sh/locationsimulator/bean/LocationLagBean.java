package com.locus.sh.locationsimulator.bean;

import java.util.List;

public class LocationLagBean {

	private LocationDistanceBean distance;
	private LocationPointBean end_location;
	private LocationPointBean start_location;
	private List<LocationStepBean> steps;

	public LocationLagBean() {
		super();
	}

	public LocationLagBean(LocationDistanceBean distance, LocationPointBean end_location, LocationPointBean start_location,
			List<LocationStepBean> steps) {
		super();
		this.distance = distance;
		this.end_location = end_location;
		this.start_location = start_location;
		this.steps = steps;
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

	public List<LocationStepBean> getSteps() {
		return steps;
	}

	public void setSteps(List<LocationStepBean> steps) {
		this.steps = steps;
	}

	@Override
	public String toString() {
		return "LocationLag [distance=" + distance + ", end_location=" + end_location + ", start_location="
				+ start_location + ", steps=" + steps + "]";
	}

}

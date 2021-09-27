package com.locus.sh.locationsimulator.model;

public class LocationPointModel {
	private Double latitude;
	private Double longitude;

	public LocationPointModel() {
		super();
	}

	public LocationPointModel(Double latitude, Double longitude) {
		super();
		this.latitude = latitude;
		this.longitude = longitude;
	}

	public Double getLatitude() {
		return latitude;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	public Double getLongitude() {
		return longitude;
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}

	@Override
	public String toString() {
		return "LocationPointModel [latitude=" + latitude + ", longitude=" + longitude + "]";
	}

}

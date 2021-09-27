package com.locus.sh.locationsimulator.bean;

public class LocationPointBean {
	private Double lat;
	private Double lng;

	public LocationPointBean() {
		super();
	}

	public LocationPointBean(Double lat, Double lng) {
		super();
		this.lat = lat;
		this.lng = lng;
	}

	public Double getLat() {
		return lat;
	}

	public void setLat(Double lat) {
		this.lat = lat;
	}

	public Double getLng() {
		return lng;
	}

	public void setLng(Double lng) {
		this.lng = lng;
	}

	@Override
	public String toString() {
		return "LocationPoint [lat=" + lat + ", lng=" + lng + "]";
	}

}

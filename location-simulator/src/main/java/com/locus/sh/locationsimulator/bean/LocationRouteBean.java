package com.locus.sh.locationsimulator.bean;

import java.util.List;

public class LocationRouteBean {
	private List<LocationLagBean> legs;
	private LocationPolyLineBean overview_polyline;

	public LocationRouteBean() {
		super();
	}

	public LocationRouteBean(List<LocationLagBean> legs, LocationPolyLineBean overview_polyline) {
		super();
		this.legs = legs;
		this.overview_polyline = overview_polyline;
	}

	public List<LocationLagBean> getLegs() {
		return legs;
	}

	public void setLegs(List<LocationLagBean> legs) {
		this.legs = legs;
	}

	public LocationPolyLineBean getOverview_polyline() {
		return overview_polyline;
	}

	public void setOverview_polyline(LocationPolyLineBean overview_polyline) {
		this.overview_polyline = overview_polyline;
	}

	@Override
	public String toString() {
		return "LocationRoute [legs=" + legs + ", overview_polyline=" + overview_polyline + "]";
	}

}

package com.locus.sh.locationsimulator.bean;

import java.util.List;

public class LocationSimulatorBean {
	private String error_message;
	private List<LocationRouteBean> routes;

	public LocationSimulatorBean() {
		super();
	}

	public LocationSimulatorBean(String error_message, List<LocationRouteBean> routes) {
		super();
		this.error_message = error_message;
		this.routes = routes;
	}

	public String getError_message() {
		return error_message;
	}

	public void setError_message(String error_message) {
		this.error_message = error_message;
	}

	public List<LocationRouteBean> getRoutes() {
		return routes;
	}

	public void setRoutes(List<LocationRouteBean> routes) {
		this.routes = routes;
	}

	@Override
	public String toString() {
		return "LocationSimulatorBean [error_message=" + error_message + ", routes=" + routes + "]";
	}

}

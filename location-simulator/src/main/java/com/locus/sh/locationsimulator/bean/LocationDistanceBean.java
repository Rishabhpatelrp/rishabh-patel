package com.locus.sh.locationsimulator.bean;

public class LocationDistanceBean {
	private String text;
	private Integer value;

	public LocationDistanceBean() {
		super();
	}

	public LocationDistanceBean(String text, Integer value) {
		super();
		this.text = text;
		this.value = value;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Integer getValue() {
		return value;
	}

	public void setValue(Integer value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return "LocationDistance [text=" + text + ", value=" + value + "]";
	}

}

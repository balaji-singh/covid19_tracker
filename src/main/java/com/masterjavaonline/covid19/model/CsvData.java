package com.masterjavaonline.covid19.model;

import java.util.List;

public class CsvData {

	private String province_state;
	private String country_region;
	private String latitude;
	private String longtude;
	private List<String> timeline;
	private int total;

	public String getProvince_state() {
		return province_state;
	}

	public void setProvince_state(String province_state) {
		this.province_state = province_state;
	}

	public String getCountry_region() {
		return country_region;
	}

	public void setCountry_region(String country_region) {
		this.country_region = country_region;
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public String getLongtude() {
		return longtude;
	}

	public void setLongtude(String longtude) {
		this.longtude = longtude;
	}

	public List<String> getTimeline() {
		return timeline;
	}

	public void setTimeline(List<String> timeline) {
		this.timeline = timeline;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	@Override
	public String toString() {
		return "CsvData [province_state=" + province_state + ", country_region=" + country_region + ", latitude="
				+ latitude + ", longtude=" + longtude + ", timeline=" + timeline + ", total=" + total + "]";
	}

}

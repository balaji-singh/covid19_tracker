package com.masterjavaonline.covid19.model;

import java.util.List;

public class GlobalData implements Comparable<GlobalData> {

	private String province_state;
	private String country_region;
	private String latitude;
	private String longtude;
	private List<String> timelineConfirmed;
	private List<String> timelineDeath;
	private List<String> timelineRecovered;
	private int totalConfirmed;
	private int totalDeath;
	private int totalRecovered;

	@Override
	public String toString() {
		return "GlobalData [province_state=" + province_state + ", country_region=" + country_region + ", latitude="
				+ latitude + ", longtude=" + longtude + ", timelineConfirmed=" + timelineConfirmed + ", timelineDeath="
				+ timelineDeath + ", timelineRecovered=" + timelineRecovered + ", totalConfirmed=" + totalConfirmed
				+ ", totalDeath=" + totalDeath + ", totalRecovered=" + totalRecovered + "]";
	}

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

	public List<String> getTimelineConfirmed() {
		return timelineConfirmed;
	}

	public void setTimelineConfirmed(List<String> timelineConfirmed) {
		this.timelineConfirmed = timelineConfirmed;
	}

	public List<String> getTimelineDeath() {
		return timelineDeath;
	}

	public void setTimelineDeath(List<String> timelineDeath) {
		this.timelineDeath = timelineDeath;
	}

	public List<String> getTimelineRecovered() {
		return timelineRecovered;
	}

	public void setTimelineRecovered(List<String> timelineRecovered) {
		this.timelineRecovered = timelineRecovered;
	}

	public int getTotalConfirmed() {
		return totalConfirmed;
	}

	public void setTotalConfirmed(int totalConfirmed) {
		this.totalConfirmed = totalConfirmed;
	}

	public int getTotalDeath() {
		return totalDeath;
	}

	public void setTotalDeath(int totalDeath) {
		this.totalDeath = totalDeath;
	}

	public int getTotalRecovered() {
		return totalRecovered;
	}

	public void setTotalRecovered(int totalRecovered) {
		this.totalRecovered = totalRecovered;
	}

	@Override
	public int compareTo(GlobalData globalData) {

		if (this.totalConfirmed > globalData.totalConfirmed) {
			return 1;
		} else if (this.totalConfirmed < globalData.totalConfirmed) {
			return -1;
		} else
			return 0;

	}

}

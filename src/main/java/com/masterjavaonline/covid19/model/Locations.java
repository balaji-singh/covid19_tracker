package com.masterjavaonline.covid19.model;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

@JsonAutoDetect
public class Locations  {

	private int id;

	private String country;

	private String country_code;

	private int country_population;

	private String province;

	private String last_updated;

	private Coordinates coordinates;

	private Latest latest;

	
	public Coordinates getCoordinates() {
		return this.coordinates;
	}

	public String getCountry() {
		return this.country;
	}

	public String getCountry_code() {
		return this.country_code;
	}

	public int getCountry_population() {
		return this.country_population;
	}

	public int getId() {
		return this.id;
	}

	public String getLast_updated() {
		return this.last_updated;
	}

	public Latest getLatest() {
		return this.latest;
	}

	public String getProvince() {
		return this.province;
	}

	public void setCoordinates(Coordinates coordinates) {
		this.coordinates = coordinates;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public void setCountry_code(String country_code) {
		this.country_code = country_code;
	}

	public void setCountry_population(int country_population) {
		this.country_population = country_population;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setLast_updated(String last_updated) {
		this.last_updated = last_updated;
	}

	public void setLatest(Latest latest) {
		this.latest = latest;
	}

	public void setProvince(String province) {
		this.province = province;
	}
}

/**
 * 
 */
package com.masterjavaonline.covid19.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

/**
 * @author balasingh
 *
 */


@JsonAutoDetect
public class Covid19Data  {
	
	private Latest latest;

    private List<Locations> locations;
    
	public Latest getLatest() {
		return latest;
	}

	public void setLatest(Latest latest) {
		this.latest = latest;
	}

	public List<Locations> getLocations() {
		return locations;
	}

	public void setLocations(List<Locations> locations) {
		this.locations = locations;
	}
	
	
	

}

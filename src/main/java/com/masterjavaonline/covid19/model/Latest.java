package com.masterjavaonline.covid19.model;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@JsonAutoDetect
public class Latest {
	
	  private String confirmed;
	  private String deaths;
	  private String recovered;
	public String getConfirmed() {
		return confirmed;
	}
	public void setConfirmed(String confirmed) {
		this.confirmed = confirmed;
	}
	public String getDeaths() {
		return deaths;
	}
	public void setDeaths(String deaths) {
		this.deaths = deaths;
	}
	public String getRecovered() {
		return recovered;
	}
	public void setRecovered(String recovered) {
		this.recovered = recovered;
	}
	@Override
	public String toString() {
		return "Covid19Latest [confirmed=" + confirmed + ", deaths=" + deaths + ", recovered=" + recovered + "]";
	}

	  
	
}

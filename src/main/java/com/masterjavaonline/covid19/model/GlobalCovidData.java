/**
 * 
 */
package com.masterjavaonline.covid19.model;

import java.util.List;

/**
 * @author balasingh
 *
 */
public class GlobalCovidData {
	
	private int totalConfirmed ;
	private int totalDeaths;
	private int totalRecovered ;
	
	private List<GlobalData> globalDatas ;

	public int getTotalConfirmed() {
		return totalConfirmed;
	}

	public void setTotalConfirmed(int totalConfirmed) {
		this.totalConfirmed = totalConfirmed;
	}

	public int getTotalDeaths() {
		return totalDeaths;
	}

	public void setTotalDeaths(int totalDeaths) {
		this.totalDeaths = totalDeaths;
	}

	public int getTotalRecovered() {
		return totalRecovered;
	}

	public void setTotalRecovered(int totalRecovered) {
		this.totalRecovered = totalRecovered;
	}

	public List<GlobalData> getGlobalDatas() {
		return globalDatas;
	}

	public void setGlobalDatas(List<GlobalData> globalDatas) {
		this.globalDatas = globalDatas;
	}

	@Override
	public String toString() {
		return "GlobalCovidData [totalConfirmed=" + totalConfirmed + ", totalDeaths=" + totalDeaths
				+ ", totalRecovered=" + totalRecovered + ", globalDatas=" + globalDatas + "]";
	}
	
	

}

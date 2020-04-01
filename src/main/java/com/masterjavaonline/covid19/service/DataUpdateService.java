package com.masterjavaonline.covid19.service;

import java.util.List;

import com.masterjavaonline.covid19.model.GlobalCovidData;
import com.masterjavaonline.covid19.model.GlobalData;

public interface DataUpdateService {

	String updateGlobalWHOData() throws Exception;

	GlobalCovidData getGlobalWHOData();

}

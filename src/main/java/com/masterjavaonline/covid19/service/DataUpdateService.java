package com.masterjavaonline.covid19.service;

import java.util.List;
import java.util.Set;

import com.masterjavaonline.covid19.model.CsvData;
import com.masterjavaonline.covid19.model.GlobalData;

public interface DataUpdateService {

	String updateGlobalWHOData() throws Exception;

	List<GlobalData> getGlobalWHOData();

}

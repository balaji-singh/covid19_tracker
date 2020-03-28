package com.masterjavaonline.covid19.service;

//CanvasjsChartService.java

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;


public interface CanvasjsChartService {

	List<List<Map<Object, Object>>> getCanvasjsChartData();

}



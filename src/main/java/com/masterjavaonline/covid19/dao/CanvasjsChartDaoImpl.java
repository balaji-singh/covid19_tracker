package com.masterjavaonline.covid19.dao;

//CanvasjsChartDaoImpl.java

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.masterjavaonline.covid19.model.CanvasjsChartData;

@Component
public class CanvasjsChartDaoImpl implements CanvasjsChartDao {

	@Override
	public List<List<Map<Object, Object>>> getCanvasjsChartData() {
		return CanvasjsChartData.getCanvasjsDataList();
	}

} 
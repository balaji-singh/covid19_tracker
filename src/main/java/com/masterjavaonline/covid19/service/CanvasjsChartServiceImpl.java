package com.masterjavaonline.covid19.service;

//CanvasjsChartServiceImpl.java


import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masterjavaonline.covid19.dao.CanvasjsChartDao;
import com.masterjavaonline.covid19.dao.CanvasjsChartDaoImpl;

@Service
public class CanvasjsChartServiceImpl implements CanvasjsChartService {

	@Autowired
	private CanvasjsChartDaoImpl canvasjsChartDao;

	public void setCanvasjsChartDao(CanvasjsChartDaoImpl canvasjsChartDao) {
		this.canvasjsChartDao = canvasjsChartDao;
	}

	@Override
	public List<List<Map<Object, Object>>> getCanvasjsChartData() {
		return canvasjsChartDao.getCanvasjsChartData();
	}

}  

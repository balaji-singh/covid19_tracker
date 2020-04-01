/**
 * 
 */
package com.masterjavaonline.covid19.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.masterjavaonline.covid19.model.Covid19Data;
import com.masterjavaonline.covid19.model.GlobalCovidData;
import com.masterjavaonline.covid19.service.Covid19ServiceImpl;
import com.masterjavaonline.covid19.service.DataUpdateServiceImpl;

/**
 * @author balasingh
 *
 */
@RestController
public class Covid19Controller {

	private static final Logger logger = LoggerFactory.getLogger(Covid19Controller.class);

	@Autowired
	private Covid19ServiceImpl covid19Service;

	@Autowired
	private DataUpdateServiceImpl dataUpdateServiceImpl;

	// @RequestMapping(value = { "/", "/home" }, method = RequestMethod.GET)
	public ModelAndView getCovidRecords(ModelAndView modelAndView) {

		Covid19Data covid19Data = covid19Service.getCovid19Data();
		logger.debug("TrackRecords from Response", covid19Data);
		modelAndView.addObject("trackRecords", covid19Data);
		modelAndView.setViewName("index");

		return modelAndView;
	}

	@RequestMapping(value = { "/update" }, method = RequestMethod.GET)
	public void updateCovidRecords() throws Exception {

		dataUpdateServiceImpl.updateGlobalWHOData();
	}

	@RequestMapping(value = { "/", "/home" }, method = RequestMethod.GET)
	public ModelAndView getGlobalCovidRecords(ModelAndView modelAndView) throws Exception {

		GlobalCovidData globalCovidData = dataUpdateServiceImpl.getGlobalWHOData();
		//Collections.sort(globalDatas, new ConfirmedCasesSort());
		 //Collections.sort(globalCovidData);
		modelAndView.addObject("trackRecords", globalCovidData);
		modelAndView.setViewName("records");
		return modelAndView;
	}

}

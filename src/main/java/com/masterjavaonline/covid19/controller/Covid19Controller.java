/**
 * 
 */
package com.masterjavaonline.covid19.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.masterjavaonline.covid19.model.GlobalCovidData;
import com.masterjavaonline.covid19.model.IndiaCovidData;
import com.masterjavaonline.covid19.service.DataUpdateServiceImpl;
import com.masterjavaonline.covid19.service.IndiaDataServiceImpl;

/**
 * @author balasingh
 *
 */
@Controller
public class Covid19Controller {

	private static final Logger logger = LoggerFactory.getLogger(Covid19Controller.class);

	@Autowired
	private DataUpdateServiceImpl dataUpdateServiceImpl;

	@Autowired
	private IndiaDataServiceImpl indiaDataServiceImpl;

	@RequestMapping(value = { "/", "/home" }, method = RequestMethod.GET)
	public ModelAndView getGlobalCovidRecords(ModelAndView modelAndView) throws Exception {
		logger.info("World data Webpage");
		GlobalCovidData globalCovidData = dataUpdateServiceImpl.getGlobalWHOData();
		modelAndView.addObject("trackRecords", globalCovidData);
		modelAndView.setViewName("home");
		return modelAndView;
	}

	@RequestMapping(value = { "/inhelp" }, method = RequestMethod.GET)
	public ModelAndView getIndiaCovidRecords(ModelAndView modelAndView) throws Exception {

		logger.info("India data Webpage");

		IndiaCovidData indiaCovidData=indiaDataServiceImpl.getCovidData();
		
		modelAndView.addObject("indiaCovidData", indiaCovidData);
		modelAndView.setViewName("inhelp");
		return modelAndView;
	}

}

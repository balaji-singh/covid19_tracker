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
import com.masterjavaonline.covid19.service.DataUpdateServiceImpl;

/**
 * @author balasingh
 *
 */
@Controller
public class Covid19Controller {

	private static final Logger logger = LoggerFactory.getLogger(Covid19Controller.class);

	@Autowired
	private DataUpdateServiceImpl dataUpdateServiceImpl;

	@RequestMapping(value = { "/", "/home" }, method = RequestMethod.GET)
	public ModelAndView getGlobalCovidRecords(ModelAndView modelAndView) throws Exception {

		GlobalCovidData globalCovidData = dataUpdateServiceImpl.getGlobalWHOData();
		modelAndView.addObject("trackRecords", globalCovidData);
		modelAndView.setViewName("home");
		return modelAndView;
	}

}

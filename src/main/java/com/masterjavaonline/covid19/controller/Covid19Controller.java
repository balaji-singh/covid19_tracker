/**
 * 
 */
package com.masterjavaonline.covid19.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;

import com.masterjavaonline.covid19.model.Covid19Data;
import com.masterjavaonline.covid19.service.Covid19ServiceImpl;

/**
 * @author balasingh
 *
 */
public class Covid19Controller implements BaseController{
	
	 private static final Logger logger = LoggerFactory.getLogger(Covid19Controller.class);
	
	@Autowired
	private Covid19ServiceImpl covid19Service ;
	
	@Override
    public ModelAndView getCovidRecords(ModelAndView modelAndView) {

		Covid19Data trackRecords = covid19Service.getCovid19Data();
        logger.debug("TrackRecords from Response", trackRecords);
        modelAndView.addObject("trackRecords", trackRecords);
        modelAndView.setViewName("byrange");

        return modelAndView;
    }

	
}

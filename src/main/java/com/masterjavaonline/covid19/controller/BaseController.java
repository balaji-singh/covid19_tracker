/**
 * 
 */
package com.masterjavaonline.covid19.controller;

import org.springframework.web.servlet.ModelAndView;

import com.masterjavaonline.covid19.model.Covid19Data;

/**
 * @author balasingh
 *
 */
public interface BaseController {

	ModelAndView getCovidRecords(ModelAndView modelAndView);

	
	
	
}

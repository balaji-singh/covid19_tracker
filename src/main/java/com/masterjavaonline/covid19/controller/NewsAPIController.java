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

import com.masterjavaonline.covid19.model.NewsData;
import com.masterjavaonline.covid19.service.NewsDataServiceImpl;

/**
 * @author balasingh
 *
 */
@Controller
public class NewsAPIController {

	private static final Logger logger = LoggerFactory.getLogger(NewsAPIController.class);

	@Autowired
	private NewsDataServiceImpl newsDataServiceImpl;

	@RequestMapping(value = { "/news" }, method = RequestMethod.GET)
	public ModelAndView getNewsModel(ModelAndView modelAndView) throws Exception {

		NewsData newsData = newsDataServiceImpl.readNews();
		modelAndView.addObject("newsData", newsData);
		modelAndView.setViewName("news");
		return modelAndView;
	}

}

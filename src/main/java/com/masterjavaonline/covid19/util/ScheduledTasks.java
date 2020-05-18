package com.masterjavaonline.covid19.util;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.masterjavaonline.covid19.service.DataUpdateServiceImpl;
import com.masterjavaonline.covid19.service.NewsDataServiceImpl;

@Component
public class ScheduledTasks {

	private static final Logger logger = LoggerFactory.getLogger(ScheduledTasks.class);

	private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

	@Autowired
	private DataUpdateServiceImpl dataUpdateServiceImpl;
	
	@Autowired
	private NewsDataServiceImpl newsDataServiceImpl;
	
	@Scheduled(fixedRate = 500000)
	public void reportCurrentTime() throws Exception {
		dataUpdateServiceImpl.updateGlobalWHOData();
		newsDataServiceImpl.getNews();
		logger.info("The time is now {}", dateFormat.format(new Date()));
	}
	
	@PostConstruct
    public void init() throws Exception{
		logger.info("Fetching Data on start up");
		dataUpdateServiceImpl.updateGlobalWHOData();
		newsDataServiceImpl.getNews();
		
    }
}

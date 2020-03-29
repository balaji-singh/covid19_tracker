package com.masterjavaonline.covid19.service;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ScheduledTasks {

	private static final Logger logger = LoggerFactory.getLogger(ScheduledTasks.class);

	private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

	@Autowired
	private DataUpdateServiceImpl dataUpdateServiceImpl;
	
	@Scheduled(fixedRate = 50000)
	public void reportCurrentTime() throws Exception {
		dataUpdateServiceImpl.updateGlobalWHOData();
		logger.info("The time is now {}", dateFormat.format(new Date()));
	}
}

package com.masterjavaonline.covid19;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

import com.masterjavaonline.covid19.service.DataUpdateServiceImpl;

@SpringBootApplication
@EnableAutoConfiguration
@EnableScheduling
public class Covid19TrackerApplication {

	@Autowired
	private DataUpdateServiceImpl dataUpdateServiceImpl;
	
	public static void main(String[] args) {
		SpringApplication.run(Covid19TrackerApplication.class, args);
	}

}

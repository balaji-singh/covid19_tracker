/**
 * 
 */
package com.masterjavaonline.covid19.service;


import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

import org.glassfish.jersey.client.ClientConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.masterjavaonline.covid19.model.Covid19Data;

/**
 * @author balasingh
 *
 */

@Service
public class Covid19ServiceImpl implements Covid19Service {

	@Value("${app.host}")
	private String host;

	@Override
	public Covid19Data getCovid19Data() {

		String uri = "https://coronavirus-tracker-api.herokuapp.com/v2/locations?source=jhu";
		

		RestTemplate restTemplate = new RestTemplate();

		Covid19Data covid19Data = restTemplate.getForObject(uri, Covid19Data.class);

		return covid19Data;
	}

	



}

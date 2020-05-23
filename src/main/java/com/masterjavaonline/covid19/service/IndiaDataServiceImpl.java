/**
 * 
 */
package com.masterjavaonline.covid19.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.masterjavaonline.covid19.model.ContactData;
import com.masterjavaonline.covid19.model.IndiaCovidData;

/**
 * @author A8100
 *
 */
@Service
public class IndiaDataServiceImpl implements IndiaDataService {

	private static final Logger logger = LoggerFactory.getLogger(NewsDataServiceImpl.class);
	String resourceUrl = "https://covid-19india-api.herokuapp.com/";
	private RestTemplate restTemplate = new RestTemplate();

	@Override
	public IndiaCovidData getCovidData() {

		IndiaCovidData indiaCovidData = new IndiaCovidData();
		ContactData contactData = getContactData();
		indiaCovidData.setContactData(contactData);

		return indiaCovidData;
	}

	private ContactData getContactData() {

		logger.info("Fetching COntacts Data");
		ResponseEntity<ContactData> response = restTemplate.getForEntity(resourceUrl + "helpline_numbers",
				ContactData.class);
		return response.getBody();
	}

}

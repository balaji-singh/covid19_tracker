/**
 * 
 */
package com.masterjavaonline.covid19.service;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.masterjavaonline.covid19.adfly.AdflyApiWrapper;
import com.masterjavaonline.covid19.model.NewsData;

/**
 * @author balasingh
 *
 */
@Service
public class NewsDataServiceImpl implements NewsDataService {

	private String directory = System.getProperty("user.home");
	private static final Logger logger = LoggerFactory.getLogger(NewsDataServiceImpl.class);

	@Override
	public NewsData getNews() {

		logger.info("getNews");
		RestTemplate restTemplate = new RestTemplate();
		AdflyApiWrapper api = new AdflyApiWrapper();
		String resourceUrl = "http://newsapi.org/v2/top-headlines?country=in&category=&apiKey=70fb93c4d52b49afba307cc244ddf0d7";

		ResponseEntity<NewsData> response = restTemplate.getForEntity(resourceUrl, NewsData.class);

		NewsData newsData = response.getBody();

		/*
		 * for (Article article : newsData.getArticles()) {
		 * 
		 * try { Shortner shortner = new
		 * ObjectMapper().readValue(api.shorten(article.getUrl()), Shortner.class);
		 * article.setUrl(shortner.getData().get(0).getShort_url()); } catch
		 * (JsonProcessingException e) { e.printStackTrace(); }
		 * 
		 * }
		 */

		ObjectMapper Obj = new ObjectMapper();
		try {

			String jsonStr = Obj.writeValueAsString(newsData);

			File newsFile = new File(directory + File.separator + "news.json");

			BufferedWriter writer = new BufferedWriter(new FileWriter(newsFile));
			writer.write(jsonStr);

			writer.close();

			logger.info("File Path:"+ newsFile.getAbsolutePath());

		}

		catch (IOException e) {
			e.printStackTrace();
		}

		return newsData;
	}

	@Override
	public NewsData readNews() {

		logger.info("readNews");
		String data = "";
		try {
			data = new String(Files.readAllBytes(Paths.get(directory + File.separator + "news.json")));

			return new ObjectMapper().readValue(data, NewsData.class);

		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return null;
	}

}

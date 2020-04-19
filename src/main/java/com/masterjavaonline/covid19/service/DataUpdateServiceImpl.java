package com.masterjavaonline.covid19.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.io.StringReader;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.springframework.stereotype.Service;

import com.masterjavaonline.covid19.model.CsvData;
import com.masterjavaonline.covid19.model.GlobalCovidData;
import com.masterjavaonline.covid19.model.GlobalData;

@Service
public class DataUpdateServiceImpl implements DataUpdateService {

	private String directory = System.getProperty("user.home");
	private String confirmedUrl = "https://raw.githubusercontent.com/CSSEGISandData/COVID-19/master/csse_covid_19_data/csse_covid_19_time_series/time_series_covid19_confirmed_global.csv";
	private String deathsUrl = "https://raw.githubusercontent.com/CSSEGISandData/COVID-19/master/csse_covid_19_data/csse_covid_19_time_series/time_series_covid19_deaths_global.csv";
	private String recoveredUrl = "https://raw.githubusercontent.com/CSSEGISandData/COVID-19/master/csse_covid_19_data/csse_covid_19_time_series/time_series_covid19_recovered_global.csv";

	@Override
	public String updateGlobalWHOData() throws Exception {

		URL urlConfirmed = new URL(confirmedUrl);
		URL urlDeaths = new URL(deathsUrl);
		URL urlRecovered = new URL(recoveredUrl);

		// File where to be downloaded
		File fileConfirmed = new File(directory + File.separator + "time_series_covid19_confirmed_global.csv");
		File fileDeaths = new File(directory + File.separator + "time_series_covid19_deaths_global.csv");
		File fileRecovered = new File(directory + File.separator + "time_series_covid19_recovered_global.csv");

		updateURLToFile(urlConfirmed, fileConfirmed);
		updateURLToFile(urlDeaths, fileDeaths);
		updateURLToFile(urlRecovered, fileRecovered);

		return null;

	}

	private void updateURLToFile(URL url, File file) {
		try {
			InputStream input = url.openStream();
			if (file.exists()) {
				if (file.isDirectory())
					throw new IOException("File '" + file + "' is a directory");

				if (!file.canWrite())
					throw new IOException("File '" + file + "' cannot be written");
			} else {
				File parent = file.getParentFile();
				if ((parent != null) && (!parent.exists()) && (!parent.mkdirs())) {
					throw new IOException("File '" + file + "' could not be created");
				}
			}

			FileOutputStream output = new FileOutputStream(file);

			byte[] buffer = new byte[4096];
			int n = 0;
			while (-1 != (n = input.read(buffer))) {
				output.write(buffer, 0, n);
			}

			input.close();
			output.close();

		} catch (IOException ioEx) {
			ioEx.printStackTrace();
		}

	}

	@Override
	public GlobalCovidData getGlobalWHOData() throws IOException, InterruptedException {
		String directory = System.getProperty("user.home");
		File fileConfirmed = new File(directory + File.separator + "time_series_covid19_confirmed_global.csv");
		File fileDeaths = new File(directory + File.separator + "time_series_covid19_deaths_global.csv");
		File fileRecovered = new File(directory + File.separator + "time_series_covid19_recovered_global.csv");

		List<CsvData> loadConfirmed = loadData(fileConfirmed);
		List<CsvData> loadDeaths = loadData(fileDeaths);
		List<CsvData> loadRecovered = loadData(fileRecovered);

		GlobalCovidData globalCovidData = new GlobalCovidData();

		int totalConfirmed = loadConfirmed.stream().mapToInt(tot-> tot.getTotal()).sum(); //getRecordsTotal(loadConfirmed);
		int totalDeaths = loadDeaths.stream().mapToInt(tot-> tot.getTotal()).sum(); //getRecordsTotal(loadDeaths);
		int totalRecovered = loadRecovered.stream().mapToInt(tot-> tot.getTotal()).sum(); //getRecordsTotal(loadRecovered);
		int totalActiveCases=totalConfirmed-totalDeaths-totalRecovered;
		
		globalCovidData.setTotalActive(totalActiveCases);
		globalCovidData.setTotalConfirmed(totalConfirmed);
		globalCovidData.setTotalDeaths(totalDeaths);
		globalCovidData.setTotalRecovered(totalRecovered);

		List<GlobalData> globalDatas = processData(loadConfirmed, loadDeaths, loadRecovered);
		globalCovidData.setGlobalDatas(globalDatas);

		return globalCovidData;

	}

	private int getRecordsTotal(List<CsvData> loadConfirmed) {

		int total = 0;

		for (CsvData csvData : loadConfirmed) {

			total = total + csvData.getTotal();

		}

		return total;
	}

	private List<GlobalData> processData(List<CsvData> loadConfirmed, List<CsvData> loadDeaths,
			List<CsvData> loadRecovered) {

		Iterator<CsvData> i1 = loadConfirmed.iterator();

		List<GlobalData> globalDatas = new ArrayList<GlobalData>();

		while (i1.hasNext()) {
			GlobalData globalData = new GlobalData();
			CsvData csvDataConfirmed = (CsvData) i1.next();
			globalData.setCountry_region(csvDataConfirmed.getCountry_region());
			globalData.setLatitude(csvDataConfirmed.getLatitude());
			globalData.setLongtude(csvDataConfirmed.getLongtude());
			globalData.setProvince_state(csvDataConfirmed.getProvince_state());
			globalData.setTimelineConfirmed(csvDataConfirmed.getTimeline());
			findDeaths(globalData, loadDeaths);
			findRecovered(globalData, loadRecovered);
			globalData.setTotalConfirmed(csvDataConfirmed.getTotal());

			globalDatas.add(globalData);

		}
		return globalDatas;

	}

	private void findRecovered(GlobalData globalData, List<CsvData> loadRecovered) {
		Iterator<CsvData> i3 = loadRecovered.iterator();
		while (i3.hasNext()) {
			CsvData csvDataRecovered = (CsvData) i3.next();

			if (globalData.getCountry_region().equals(csvDataRecovered.getCountry_region())
					&& globalData.getProvince_state().equals(csvDataRecovered.getProvince_state())) {
				globalData.setTimelineRecovered(csvDataRecovered.getTimeline());
				globalData.setTotalRecovered(csvDataRecovered.getTotal());

			}
		}

	}

	private void findDeaths(GlobalData globalData, List<CsvData> loadDeaths) {
		Iterator<CsvData> i2 = loadDeaths.iterator();
		while (i2.hasNext()) {
			CsvData csvDataDeaths = (CsvData) i2.next();

			if (globalData.getCountry_region().equals(csvDataDeaths.getCountry_region())
					&& globalData.getProvince_state().equals(csvDataDeaths.getProvince_state())) {
				globalData.setTimelineDeath(csvDataDeaths.getTimeline());
				globalData.setTotalDeath(csvDataDeaths.getTotal());

			}
		}

	}

	private List<CsvData> loadData(File file) throws IOException, InterruptedException {

		List<CsvData> csvDatas = new ArrayList<CsvData>();
		Reader csvDataReader = new FileReader(file);
		Iterable<CSVRecord> records = CSVFormat.RFC4180.parse(csvDataReader);

		for (CSVRecord record : records) {
			CsvData csvData = new CsvData();
			String country=record.get(1).replaceAll("\\s+", "_");
			csvData.setProvince_state(record.get(0));
			csvData.setCountry_region(country);
			try {
				csvData.setTotal(Integer.parseInt(record.get(record.size() - 1)));
			} catch (Exception ex) {
			}
			csvData.setLatitude(record.get(2));
			csvData.setLongtude(record.get(3));
			List<String> timeline = new ArrayList<String>();
			for (int i = 4; i < record.size(); i++) {

				timeline.add(record.get(i));
			}
			csvData.setTimeline(timeline);
			csvDatas.add(csvData);

		}

		return csvDatas;
	}

}

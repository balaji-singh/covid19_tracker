package com.masterjavaonline.covid19.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.masterjavaonline.covid19.model.CsvData;
import com.masterjavaonline.covid19.model.GlobalData;

@Service
public class DataUpdateServiceImpl implements DataUpdateService {

	@Value("${url.confirmed}")
	private String confirmed;
	@Value("${url.deaths}")
	private String deaths;
	@Value("${url.recovered}")
	private String recovered;

	@Override
	public String updateGlobalWHOData() throws Exception {

		String confirmedUrl = "https://raw.githubusercontent.com/CSSEGISandData/COVID-19/master/csse_covid_19_data/csse_covid_19_time_series/time_series_covid19_confirmed_global.csv";
		String deathsUrl = "https://raw.githubusercontent.com/CSSEGISandData/COVID-19/master/csse_covid_19_data/csse_covid_19_time_series/time_series_covid19_deaths_global.csv";
		String recoveredUrl = "https://raw.githubusercontent.com/CSSEGISandData/COVID-19/master/csse_covid_19_data/csse_covid_19_time_series/time_series_covid19_recovered_global.csv";

		URL urlConfirmed = new URL(confirmedUrl);
		URL urlDeaths = new URL(deathsUrl);
		URL urlRecovered = new URL(recoveredUrl);

		// File where to be downloaded
		File fileConfirmed = new File("time_series_covid19_confirmed_global.csv");
		File fileDeaths = new File("time_series_covid19_deaths_global.csv");
		File fileRecovered = new File("time_series_covid19_recovered_global.csv");

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

			System.out.println("File '" + file + "' downloaded successfully!");
		} catch (IOException ioEx) {
			ioEx.printStackTrace();
		}

	}

	@Override
	public List<GlobalData> getGlobalWHOData() {

		File fileConfirmed = new File("time_series_covid19_confirmed_global.csv");
		File fileDeaths = new File("time_series_covid19_deaths_global.csv");
		File fileRecovered = new File("time_series_covid19_recovered_global.csv");

		List<CsvData> loadConfirmed = loadData(fileConfirmed);
		List<CsvData> loadDeaths = loadData(fileDeaths);
		List<CsvData> loadRecovered = loadData(fileRecovered);

		List<GlobalData> globalDatas = processData(loadConfirmed, loadDeaths, loadRecovered);

		
		return globalDatas;

	}

	private List<GlobalData> processData(List<CsvData> loadConfirmed, List<CsvData> loadDeaths,
			List<CsvData> loadRecovered) {

		Iterator<CsvData> i1 = loadConfirmed.iterator();
		Iterator<CsvData> i2 = loadDeaths.iterator();
		Iterator<CsvData> i3 = loadRecovered.iterator();
		List<GlobalData> globalDatas = new ArrayList<GlobalData>();

		while (i1.hasNext() && i2.hasNext() && i3.hasNext()) {
			GlobalData globalData = new GlobalData();
			CsvData csvDataConfirmed = (CsvData) i1.next();
			CsvData csvDataDeaths = (CsvData) i2.next();
			CsvData csvDataRecovered = (CsvData) i3.next();
			if (csvDataConfirmed.getCountry_region().equals(csvDataDeaths.getCountry_region())
					&& csvDataConfirmed.getCountry_region().equals(csvDataRecovered.getCountry_region())) {

				globalData.setCountry_region(csvDataConfirmed.getCountry_region());
				globalData.setLatitude(csvDataConfirmed.getLatitude());
				globalData.setLongtude(csvDataConfirmed.getLongtude());
				globalData.setProvince_state(csvDataConfirmed.getProvince_state());
				globalData.setTimelineConfirmed(csvDataConfirmed.getTimeline());
				globalData.setTimelineDeath(csvDataDeaths.getTimeline());
				globalData.setTimelineRecovered(csvDataRecovered.getTimeline());
				globalData.setTotalConfirmed(csvDataConfirmed.getTotal());
				globalData.setTotalDeath(csvDataDeaths.getTotal());
				globalData.setTotalRecovered(csvDataRecovered.getTotal());

			}

			globalDatas.add(globalData);

		}
		return globalDatas;

	}

	private List<CsvData> loadData(File file) {

		BufferedReader br = null;
		String line = "";
		String cvsSplitBy = ",";
		List<CsvData> csvDatas = new ArrayList<CsvData>();

		try {
			int counter = 0;
			br = new BufferedReader(new FileReader(file));

			while ((line = br.readLine()) != null) {
				CsvData csvData = new CsvData();
				String[] csvFileData = line.split(cvsSplitBy);
				System.out.println("\n");

				csvData.setProvince_state(csvFileData[0]);
				csvData.setCountry_region(csvFileData[1]);
				csvData.setLatitude(csvFileData[2]);
				csvData.setLongtude(csvFileData[3]);

				List<String> timeline = new ArrayList<String>();
				for (int i = 4; i < csvFileData.length; i++) {

					timeline.add(csvFileData[i]);
				}
				if (counter > 0) {
					csvData.setTotal(Integer.parseInt(timeline.get(timeline.size() - 1)));
				}

				csvData.setTimeline(timeline);
				System.out.println(csvData);
				csvDatas.add(csvData);
				counter++;

			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return csvDatas;
	}

}

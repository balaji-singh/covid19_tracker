package com.masterjavaonline.covid19.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.masterjavaonline.covid19.model.CsvData;

public class URLReader {

	public static void copyURLToFile(File file) {

		BufferedReader br = null;
		String line = "";
		String cvsSplitBy = ",";
		CsvData csvData = new CsvData();
		
		try {
			int counter =0;
			br = new BufferedReader(new FileReader(file));

			while ((line = br.readLine()) != null) {

				String[] country = line.split(cvsSplitBy);
				System.out.println("\n");
				List<CsvData> data = new ArrayList<CsvData>();

				csvData.setProvince_state(country[0]);
				csvData.setCountry_region(country[1]);
				csvData.setLatitude(country[2]);
				csvData.setLongtude(country[3]);

				List<String> timeline = new ArrayList<String>();
				for (int i = 4; i < country.length; i++) {

					timeline.add(country[i]);
				}
				if(counter>0) {
					csvData.setTotal(Integer.parseInt(timeline.get(timeline.size()-1)));
				}
				
				csvData.setTimeline(timeline);

				
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

	}

	public static void main(String[] args) throws IOException {

		// File where to be downloaded
		File fileConfirmed = new File("time_series_covid19_confirmed_global.csv");
		File fileDeaths = new File("time_series_covid19_deaths_global.csv");
		File fileRecovered = new File("time_series_covid19_recovered_global.csv");

		URLReader.copyURLToFile(fileConfirmed);
		// URLReader.copyURLToFile(fileDeaths);
		// URLReader.copyURLToFile(fileRecovered);
	}

}

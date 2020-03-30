package com.masterjavaonline.covid19.util;

import java.util.Comparator;

import com.masterjavaonline.covid19.model.GlobalData;

public class ConfirmedCasesSort implements Comparator<GlobalData> {

	@Override
	public int compare(GlobalData globalData1, GlobalData globalData2) {
		return globalData1.getTotalConfirmed() - globalData2.getTotalConfirmed();
	}

}

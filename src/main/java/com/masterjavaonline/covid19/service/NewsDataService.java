/**
 * 
 */
package com.masterjavaonline.covid19.service;

import com.masterjavaonline.covid19.model.NewsData;

/**
 * @author balasingh
 *
 */
public interface NewsDataService {

	NewsData getNews();

	NewsData readNews();

}

/**
 * 
 */
package com.masterjavaonline.covid19.model;

import java.util.List;

/**
 * @author balasingh
 *
 */
public class Shortner {

	private List<Object> errors = null;
	private List<Object> warnings = null;
	private List<Datum> data = null;
	private Page page;

	public List<Object> getErrors() {
		return errors;
	}

	public void setErrors(List<Object> errors) {
		this.errors = errors;
	}

	public List<Object> getWarnings() {
		return warnings;
	}

	public void setWarnings(List<Object> warnings) {
		this.warnings = warnings;
	}

	public List<Datum> getData() {
		return data;
	}

	public void setData(List<Datum> data) {
		this.data = data;
	}

	public Page getPage() {
		return page;
	}

	public void setPage(Page page) {
		this.page = page;
	}

	
}

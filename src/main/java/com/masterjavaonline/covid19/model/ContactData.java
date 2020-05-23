/**
 * 
 */
package com.masterjavaonline.covid19.model;

import java.util.List;

/**
 * @author A8100
 *
 */
public class ContactData {

	private List<ContactDetail> contact_details = null;
	private String helpline_email;
	private String helpline_number;
	private String source;
	private String toll_free;
	public List<ContactDetail> getContact_details() {
		return contact_details;
	}
	public void setContact_details(List<ContactDetail> contact_details) {
		this.contact_details = contact_details;
	}
	public String getHelpline_email() {
		return helpline_email;
	}
	public void setHelpline_email(String helpline_email) {
		this.helpline_email = helpline_email;
	}
	public String getHelpline_number() {
		return helpline_number;
	}
	public void setHelpline_number(String helpline_number) {
		this.helpline_number = helpline_number;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public String getToll_free() {
		return toll_free;
	}
	public void setToll_free(String toll_free) {
		this.toll_free = toll_free;
	}

	
}

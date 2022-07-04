/**
 * 
 */
package com.sogeti.filmland.request.model;

/**
 * @author monal500
 *
 */
public class UserSubscribeRequestModel {

	private String emailId;
	private String availableCategory;

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getAvailableCategory() {
		return availableCategory;
	}

	public void setAvailableCategory(String availableCategory) {
		this.availableCategory = availableCategory;
	}

}

/**
 * 
 */
package com.sogeti.filmland.dto;

import java.io.Serializable;
import java.util.Date;

/**
 * @author monal500
 *
 */
public class SubscribedCategoryDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public SubscribedCategoryDto(String name, long remainingContent, long price, Date startDate) {
		this.name = name;
		this.remainingContent = remainingContent;
		this.price = price;
		this.startDate = startDate;
	}

	private String name;
	private long remainingContent;
	private long price;
	private Date startDate;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public long getRemainingContent() {
		return remainingContent;
	}

	public void setRemainingContent(long remainingContent) {
		this.remainingContent = remainingContent;
	}

	public long getPrice() {
		return price;
	}

	public void setPrice(long price) {
		this.price = price;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	@Override
	public String toString() {
		return "SubscribedCategory [name=" + name + ", remainingContent=" + remainingContent + ", price=" + price
				+ ", startDate=" + startDate + "]";
	}

}

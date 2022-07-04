/**
 * 
 */
package com.sogeti.filmland.dto;

import java.io.Serializable;

/**
 * @author monal500
 *
 */
public class AvailableCategoryDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public AvailableCategoryDto(String name, long availableContent, long price) {
		this.name = name;
		this.availableContent = availableContent;
		this.price = price;
	}

	private String name;
	private long availableContent;
	private long price;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public long getAvailableContent() {
		return availableContent;
	}

	public void setAvailableContent(long availableContent) {
		this.availableContent = availableContent;
	}

	public long getPrice() {
		return price;
	}

	public void setPrice(long price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "AvailableCategoryDto [name=" + name + ", availableContent=" + availableContent + ", price=" + price
				+ "]";
	}

}

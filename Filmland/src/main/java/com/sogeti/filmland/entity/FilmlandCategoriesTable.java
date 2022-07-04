/**
 * 
 */
package com.sogeti.filmland.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author monal500
 *
 */
@Entity
@Table(name = "filmland_category")
public class FilmlandCategoriesTable implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	private long categoryId;
	
	@Column(nullable = false)
	private String categoryName;
	
	@Column(nullable = false)
	private long availableContent;
	
	@Column(nullable = false)
	private long price;

	public long getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(long categoryId) {
		this.categoryId = categoryId;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
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
	
	
}

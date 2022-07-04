/**
 * 
 */
package com.sogeti.filmland.response.model;

import java.util.List;

import com.sogeti.filmland.dto.AvailableCategoryDto;
import com.sogeti.filmland.dto.SubscribedCategoryDto;

/**
 * @author monal500
 *
 */
public class CategoryResponseModel {
	
	private List<AvailableCategoryDto> availableCategoryDtos;
	private List<SubscribedCategoryDto> subscribedCategoryDtos;
	public List<AvailableCategoryDto> getAvailableCategoryDtos() {
		return availableCategoryDtos;
	}
	public void setAvailableCategoryDtos(List<AvailableCategoryDto> availableCategoryDtos) {
		this.availableCategoryDtos = availableCategoryDtos;
	}
	public List<SubscribedCategoryDto> getSubscribedCategoryDtos() {
		return subscribedCategoryDtos;
	}
	public void setSubscribedCategoryDtos(List<SubscribedCategoryDto> subscribedCategoryDtos) {
		this.subscribedCategoryDtos = subscribedCategoryDtos;
	}
	
	

}

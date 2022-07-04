/**
 * 
 */
package com.sogeti.filmland.service;

import com.sogeti.filmland.response.model.CategoryResponseModel;
import com.sogeti.filmland.response.model.SubscprtionShareResponseModel;
import com.sogeti.filmland.response.model.UserResponseModel;

/**
 * @author monal500
 *
 */
public interface CategoryService {
	CategoryResponseModel getCategoryDetails(String userId);
	UserResponseModel processUserSubscrption(String emailId,String availableCategory);
	SubscprtionShareResponseModel processShareSubscrption(String emailId,String consumerId,String availableCategory);

}

/**
 * 
 */
package com.sogeti.filmland.service;


import com.sogeti.filmland.dto.UserDto;
import com.sogeti.filmland.entity.UserDataTable;
import com.sogeti.filmland.response.model.UserResponseModel;

/**
 * @author monal500
 *
 */
public interface UserService {
	
	UserResponseModel createUser(UserDto userDto);
	UserResponseModel loginUser(UserDto userDto);

}

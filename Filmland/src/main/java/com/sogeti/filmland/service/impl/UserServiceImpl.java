/**
 * 
 */
package com.sogeti.filmland.service.impl;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sogeti.filmland.db.service.FilmlandDBService;
import com.sogeti.filmland.dto.UserDto;
import com.sogeti.filmland.entity.UserDataTable;
import com.sogeti.filmland.response.model.UserResponseModel;
import com.sogeti.filmland.service.UserService;
import com.sogeti.filmland.utils.FilmlandUtils;

/**
 * @author monal500
 *
 */
@Service
public class UserServiceImpl implements UserService {

	private Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

	@Autowired
	FilmlandDBService dbService;

	@Override
	public UserResponseModel createUser(UserDto userDto) {
		// TODO Auto-generated method stub
		UserResponseModel response = new UserResponseModel();
		UserDataTable dataTable = dbService.findIfUserRegistered(userDto.getEmail());
		if (dataTable != null) {
			logger.info(userDto.getEmail() + " is already a registred User!!");
			response.setEmail(userDto.getEmail());
			response.setMessage("USER NOT CREATED ,AS USER IS ALREADY REGISTERED!");
		}

		else {

			logger.info(userDto.getEmail() + " is not registered User");

			UserDataTable userDataTable = new ModelMapper().map(userDto, UserDataTable.class);

			String userId = FilmlandUtils.generateUserId(10);
			userDataTable.setUserId(userId);
			userDataTable.setEncryptedPassword(userDto.getPassword());

			dbService.insertDataIntoUserTable(userDataTable);

			logger.info("User successfully created!!");

			response.setEmail(userDto.getEmail());
			response.setMessage("USER CREATED SUCCESSFULLY");
		}
		return response;
	}

	@Override
	public UserResponseModel loginUser(UserDto userDto) {
		// TODO Auto-generated method stub
		UserResponseModel response = new UserResponseModel();

		boolean validLogin = dbService.loginDetails(userDto.getEmail(), userDto.getPassword());
		if (validLogin == true) {
			logger.info("USER PRESENT");
			response.setEmail(userDto.getEmail());
			response.setMessage("VALID USER");
		} else {
			logger.info("USER NOT PRESENT");
			response.setEmail(userDto.getEmail());
			response.setMessage("INVALID USER");
		}
		return response;
	}

}

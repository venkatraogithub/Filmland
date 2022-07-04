/**
 * 
 */
package com.sogeti.filmland.controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sogeti.filmland.dto.UserDto;
import com.sogeti.filmland.request.model.UserCreateRequestModel;
import com.sogeti.filmland.request.model.UserLoginRequestModel;
import com.sogeti.filmland.response.model.UserResponseModel;
import com.sogeti.filmland.service.UserService;

/**
 * @author monal500
 *
 */
@RestController
@RequestMapping("/create")
public class CreateUserController {

	@Autowired
	private UserService userService;

	@PostMapping(consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<UserResponseModel> createUser(@RequestBody UserCreateRequestModel userCreateModel) {

		ModelMapper modelMapper = new ModelMapper();
		UserDto userDto = modelMapper.map(userCreateModel, UserDto.class);

		return new ResponseEntity<UserResponseModel>(userService.createUser(userDto), HttpStatus.CREATED);
	}

	@PostMapping(path = "/validate", consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = {
			MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<UserResponseModel> userLogin(@RequestBody UserLoginRequestModel userLoginModel) {
		UserDto userDto = new ModelMapper().map(userLoginModel, UserDto.class);

		return new ResponseEntity<UserResponseModel>(userService.loginUser(userDto), HttpStatus.OK);

	}

}

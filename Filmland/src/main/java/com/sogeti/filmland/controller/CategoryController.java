/**
 * 
 */
package com.sogeti.filmland.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sogeti.filmland.request.model.SubscrptionShareRequestModel;
import com.sogeti.filmland.request.model.UserSubscribeRequestModel;
import com.sogeti.filmland.response.model.CategoryResponseModel;
import com.sogeti.filmland.response.model.SubscprtionShareResponseModel;
import com.sogeti.filmland.response.model.UserResponseModel;
import com.sogeti.filmland.service.CategoryService;

/**
 * @author monal500
 *
 */
@RestController
@RequestMapping("/category")
public class CategoryController {

	@Autowired
	private CategoryService categoryService;

	@GetMapping(path = "/{id}")
	public ResponseEntity<CategoryResponseModel> getCategory(@PathVariable String id) {

		return new ResponseEntity<CategoryResponseModel>(categoryService.getCategoryDetails(id), HttpStatus.OK);

	}

	@PostMapping(path = "/{subscribe}", consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = {
			MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<UserResponseModel> processSubscribe(@RequestBody UserSubscribeRequestModel userModel) {

		return new ResponseEntity<UserResponseModel>(
				categoryService.processUserSubscrption(userModel.getEmailId(), userModel.getAvailableCategory()),
				HttpStatus.CREATED);

	}

	@PostMapping(path = "/share", consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = {
			MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<SubscprtionShareResponseModel> processShareSubcribe(
			@RequestBody SubscrptionShareRequestModel subShare) {

		return new ResponseEntity<SubscprtionShareResponseModel>(categoryService.processShareSubscrption(
				subShare.getEmail(), subShare.getCustomer(), subShare.getSubscribedCategory()), HttpStatus.CREATED);

	}

}

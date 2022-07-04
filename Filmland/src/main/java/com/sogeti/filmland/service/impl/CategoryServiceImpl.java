package com.sogeti.filmland.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sogeti.filmland.db.service.FilmlandDBService;
import com.sogeti.filmland.dto.AvailableCategoryDto;
import com.sogeti.filmland.dto.SubscribedCategoryDto;
import com.sogeti.filmland.entity.FilmlandCategoriesTable;
import com.sogeti.filmland.entity.FilmlandSubscrptionTable;
import com.sogeti.filmland.entity.UserDataTable;
import com.sogeti.filmland.response.model.CategoryResponseModel;
import com.sogeti.filmland.response.model.SubscprtionShareResponseModel;
import com.sogeti.filmland.response.model.UserResponseModel;
import com.sogeti.filmland.service.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService {

	private Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

	@Autowired
	FilmlandDBService dbService;


	@Override
	public CategoryResponseModel getCategoryDetails(String emailId) {
		// TODO Auto-generated method stub
		CategoryResponseModel categoryResponseModel = new CategoryResponseModel();
		UserDataTable dataTable = dbService.findIfUserRegistered(emailId);

		if (dataTable != null) {
			logger.info("User is valid");
			String userId = dataTable.getUserId();

			List<SubscribedCategoryDto> subscribedList = new ArrayList<>();
			List<AvailableCategoryDto> unSubscribedList = new ArrayList<>();
			List<Long> subscribedCategoryList = new ArrayList<>();

			List<FilmlandSubscrptionTable> subDataList = dbService.getUserSubDetails(userId);

			if (subDataList.size() > 0) {
				logger.info("Number of catgeories user is subscribed to :" + subDataList.size());

				subDataList.stream().forEach(t -> {
					subscribedList.add(new SubscribedCategoryDto(t.getCategoryName(), t.getUsedContent(), t.getPrice(),
							t.getSubscribedDate()));
					subscribedCategoryList.add(t.getCategoryId());

				});
			} else {
				logger.info("User is not subscribed to any categories");
			}

			List<FilmlandCategoriesTable> unSubDataList = dbService.getUserUnSubDetails(subscribedCategoryList);

			unSubDataList.stream().forEach(t -> {
				unSubscribedList
						.add(new AvailableCategoryDto(t.getCategoryName(), t.getAvailableContent(), t.getPrice()));
			});
			categoryResponseModel.setSubscribedCategoryDtos(subscribedList);
			categoryResponseModel.setAvailableCategoryDtos(unSubscribedList);
		} else {
			logger.info("Invalid user");
		}
		return categoryResponseModel;
	}

	@Override
	public UserResponseModel processUserSubscrption(String emailId, String availableCategory) {

		UserResponseModel responseModel = new UserResponseModel();

		FilmlandCategoriesTable category = dbService.findBycategoryName(availableCategory);

		if (category != null) {
			logger.info("Category is present in filmLand");

			UserDataTable userData = dbService.findIfUserRegistered(emailId);

			if (userData != null) {
				logger.info("User is present");

				FilmlandSubscrptionTable subscprtionCheck = dbService.getSubDataByUserIdAndCategory(userData.getUserId(),availableCategory);

				if (subscprtionCheck != null) {
					logger.info("User already subscribed to this category");
					responseModel.setEmail(emailId);
					responseModel.setMessage("User already subscribed to category " + availableCategory);

				} else {

					logger.info("This user is not subscribed to the category");

					// TODO Auto-generated method stub
					FilmlandSubscrptionTable subscrption = new FilmlandSubscrptionTable();
					subscrption.setCategoryId(category.getCategoryId());
					subscrption.setCategoryName(availableCategory);
					subscrption.setPrice(0);
					subscrption.setSubscribedDate(new Date());
					subscrption.setUsedContent(category.getAvailableContent());
					subscrption.setUserId(userData.getUserId());

					dbService.insertIntoSubscriberTable(subscrption);

					logger.info("User successfully subscribed to the category");

					responseModel.setEmail(emailId);
					responseModel.setMessage("Successfully subscribed to category " + availableCategory);

				}

			} else {
				logger.info("Invalid User");
				responseModel.setEmail(emailId);
				responseModel.setMessage("Invalid user");
			}
		} else {
			logger.info("Provided Category is not present " + availableCategory);
			responseModel.setEmail(emailId);
			responseModel.setMessage("Category Not present " + availableCategory);
		}
		return responseModel;
	}

	@Override
	public SubscprtionShareResponseModel processShareSubscrption(String emailId, String consumerId, String availableCategory) {
		// TODO Auto-generated method stub
		
		SubscprtionShareResponseModel response = new SubscprtionShareResponseModel();
		UserDataTable dataTable = dbService.findIfUserRegistered(emailId);
		UserDataTable custData = dbService.findIfUserRegistered(consumerId);
		FilmlandCategoriesTable catTable = dbService.findBycategoryName(availableCategory);

		if (dataTable != null && custData != null && catTable!=null) {
			logger.info("User is valid");
			String userId = dataTable.getUserId();
			FilmlandSubscrptionTable subData = dbService.getSubDataByUserIdAndCategory(userId, availableCategory);
			FilmlandSubscrptionTable customerSubData = dbService.getSubDataByUserIdAndCategory(consumerId,
					availableCategory);
			if (subData != null) {
				logger.info("User has a valid subscrption");
				if (customerSubData != null) {
					logger.info("Customer already has subscprtion to this category , rejecting this case");
					response.setMessage("Sharing unsuccessful");
					response.setStatus("Customer is already sub from the category");
				} else {
					logger.info("Splitting subscrption between user and consumer");

					long newUserPrice = subData.getPrice() / 2;
					long newCustomerPrice = subData.getPrice() - newUserPrice;

					long newAvailableContent = subData.getUsedContent() / 2;
					long newCustomerContent = subData.getUsedContent() - newAvailableContent;
					
					FilmlandSubscrptionTable customerData = new FilmlandSubscrptionTable();
					customerData.setCategoryId(subData.getCategoryId());
					customerData.setCategoryName(availableCategory);
					customerData.setPrice(newCustomerPrice);
					customerData.setSubscribedDate(subData.getSubscribedDate());
					customerData.setUsedContent(newCustomerContent);
					customerData.setUserId(custData.getUserId());
					
					dbService.insertIntoSubscriberTable(customerData);
					
					int updateQuery = dbService.updateSubTable(newUserPrice, newAvailableContent, userId);
					
					logger.info("Subscrption successfully shared ");
					response.setMessage("Sharing successful");
					response.setStatus("Subscrption is divided between user and customer");
				}
			} else {
				logger.info("User has no valid subscrptions");
				response.setMessage("Sharing unsuccessful");
				response.setStatus("User has no valid subscrptions");
			}
		} else {
			logger.info("User or customer is invalid / Category is not valid");
			response.setMessage("Sharing unsuccessful");
			response.setStatus("User or customer is invalid ,/ Category is not valid");
		}

		return response;
	}
}

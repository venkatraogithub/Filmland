/**
 * 
 */
package com.sogeti.filmland.db.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sogeti.filmland.entity.FilmlandCategoriesTable;
import com.sogeti.filmland.entity.FilmlandSubscrptionTable;
import com.sogeti.filmland.entity.UserDataTable;
import com.sogeti.filmland.repo.FilmlandCategoriesRepo;
import com.sogeti.filmland.repo.FilmlandSubscrptionRepo;
import com.sogeti.filmland.repo.UserDataRepo;

/**
 * @author monal500
 *
 */
@Service
public class FilmlandDBService {

	@Autowired
	private UserDataRepo userDataRepo;

	@Autowired
	private FilmlandSubscrptionRepo subscrptionRepo;

	@Autowired
	private FilmlandCategoriesRepo categoriesRepo;

	public UserDataTable findIfUserRegistered(String emailId) {
		return userDataRepo.findByEmail(emailId);
	}

	public void insertDataIntoUserTable(UserDataTable userdataTable) {
		userDataRepo.save(userdataTable);
	}

	public boolean loginDetails(String emailId, String password) {
		UserDataTable dataTable = userDataRepo.getLoginDetails(emailId, password);
		if (dataTable != null) {
			return true;
		} else
			return false;
	}

	public List<FilmlandSubscrptionTable> getUserSubDetails(String userId) {
		return subscrptionRepo.getUserData(userId);
	}

	public List<FilmlandCategoriesTable> getUserUnSubDetails(List<Long> subIds) {
		return categoriesRepo.getUnSubData(subIds);
	}

	public UserDataTable findByUserId(String userId) {
		return userDataRepo.findByUserId(userId);
	}

	public FilmlandCategoriesTable findBycategoryName(String categoryName) {
		return categoriesRepo.findBycategoryName(categoryName);
	}

	public FilmlandSubscrptionTable findSubscrptionsByUserId(String userId) {
		return subscrptionRepo.findByuserId(userId);
	}

	public void insertIntoSubscriberTable(FilmlandSubscrptionTable subscrptionTable) {
		subscrptionRepo.save(subscrptionTable);
	}

	public FilmlandSubscrptionTable getSubDataByUserIdAndCategory(String userId, String availableCategory) {
		return subscrptionRepo.getDataByUserIdAndCategory(userId, availableCategory);
	}

	public int updateSubTable(long newPrice, long newContent, String userId) {
		return subscrptionRepo.updateSubTable(newPrice, newContent, userId);
	}

}

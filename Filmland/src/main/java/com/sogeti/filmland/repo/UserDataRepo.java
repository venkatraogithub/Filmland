/**
 * 
 */
package com.sogeti.filmland.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.sogeti.filmland.entity.UserDataTable;

/**
 * @author monal500
 *
 */
@Repository
public interface UserDataRepo extends JpaRepository<UserDataTable, Long>{
	
	UserDataTable findByEmail(String email);
	
	@Query(value="select u from UserDataTable u where u.email = :email and u.encryptedPassword = :password")
	UserDataTable getLoginDetails(@Param("email") String email,@Param("password") String password);
	
	UserDataTable findByUserId(String userId);

}

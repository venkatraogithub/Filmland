/**
 * 
 */
package com.sogeti.filmland.repo;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.sogeti.filmland.entity.FilmlandSubscrptionTable;

/**
 * @author monal500
 *
 */
public interface FilmlandSubscrptionRepo extends JpaRepository<FilmlandSubscrptionTable, Long>{
	
	@Query(value = "select f from FilmlandSubscrptionTable f where f.userId = :userid")
	List<FilmlandSubscrptionTable> getUserData(@Param("userid") String userid);
	
	FilmlandSubscrptionTable findByuserId(String userId);
	
	@Query(value = "select f from FilmlandSubscrptionTable f where f.userId = :userid and f.categoryName = :availableCategory")
	FilmlandSubscrptionTable getDataByUserIdAndCategory(@Param("userid")String userid,@Param("availableCategory") String availableCategory);
	
	@Modifying(clearAutomatically = true)
	@Transactional
	@Query(value = "update FilmlandSubscrptionTable fs set fs.price = :newPrice , fs.UsedContent = :newContent where fs.userId = :userId")
	int updateSubTable(@Param("newPrice") long newPrice,@Param("newContent") long newContent,@Param("userId") String userId);

}

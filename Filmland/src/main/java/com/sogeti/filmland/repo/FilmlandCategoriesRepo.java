package com.sogeti.filmland.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.sogeti.filmland.entity.FilmlandCategoriesTable;

@Repository
public interface FilmlandCategoriesRepo extends JpaRepository<FilmlandCategoriesTable, Long>{
	
	@Query(value = "select f from FilmlandCategoriesTable f where f.categoryId not in :listOfId")
	List<FilmlandCategoriesTable> getUnSubData(@Param("listOfId") List<Long>listOfId);
	
	FilmlandCategoriesTable findBycategoryName(String categoryName);

}

package com.gl.smartlms.repository;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import com.gl.smartlms.model.Category;
@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

	public List<Category> findAllByOrderByNameAsc();

	
	
	
}

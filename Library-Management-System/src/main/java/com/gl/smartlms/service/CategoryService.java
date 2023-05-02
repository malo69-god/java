package com.gl.smartlms.service;


import java.util.Optional;

import org.springframework.stereotype.Service;

import com.gl.smartlms.model.Category;



@Service
public interface CategoryService {

	
	public Category addNew(Category category);

	public Category save(Category category);

	public Optional<Category> getCategory(Long id);
}

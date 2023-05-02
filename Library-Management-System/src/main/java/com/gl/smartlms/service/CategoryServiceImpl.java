package com.gl.smartlms.service;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gl.smartlms.model.Category;
import com.gl.smartlms.repository.CategoryRepository;

@Service
public class CategoryServiceImpl implements CategoryService{

	
	@Autowired
	private CategoryRepository categoryRepository;
	@Override
	public Category addNew(Category category) {
			category.setCreateDate(new Date());
		return categoryRepository.save(category);
	}
	
	@Override
	public Category save(Category category) {
		return categoryRepository.save(category);}



	@Override
	public Optional<Category> getCategory(Long id) {
		return categoryRepository.findById(id);

}

}
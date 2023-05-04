package com.gl.smartlms.restController;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gl.smartlms.constants.Constants;
import com.gl.smartlms.model.Category;

import com.gl.smartlms.service.CategoryService;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

@RestController
@RequestMapping("/category")
public class CategoryRestController {

	@Autowired
	private CategoryService categoryService;

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public ResponseEntity<String> saveCategory(@Valid @RequestBody Category category) {

		category = categoryService.addNew(category);

		return new ResponseEntity<String>("Category Added with type " + category.getName(), HttpStatus.CREATED);

	}
	
	
	@PutMapping("/update")
	public ResponseEntity<String> updateMember(@Valid @RequestBody Category category){
		
		Optional<Category> optional = categoryService.getCategory(category.getId());
		
		if(optional.isPresent()) {
			category.setCreateDate(optional.get().getCreateDate());
			categoryService.save(category);
			
			return new ResponseEntity<String>("Category Updated With Type " + optional.get().getName(),HttpStatus.ACCEPTED);
		}
		
		return  new  ResponseEntity<String>("Member Not Found",HttpStatus.NOT_FOUND);
		
	}
	
	
	@GetMapping("/list")
	public ResponseEntity<List<Category>> showAllMembers() {

		List<Category> clist = categoryService.getAll();
		if(clist != null) {
			return new ResponseEntity<List<Category>>(clist, HttpStatus.FOUND);
			
		}
		return new ResponseEntity<List<Category>>(HttpStatus.NO_CONTENT);
	}
	
	
	
	@GetMapping("/sorted/list")
	public ResponseEntity<List<Category>> showAllMembersSortedByName() {

		List<Category> clist = categoryService.getAllBySort();
		if(clist != null) {
			return new ResponseEntity<List<Category>>(clist, HttpStatus.FOUND);
			
		}
		return new ResponseEntity<List<Category>>(HttpStatus.NO_CONTENT);
	}
	

	
	
	@GetMapping("/{id}")
	public ResponseEntity<String> findCategoryById(@PathVariable Long id) {
		Optional<Category> optional = categoryService.getCategory(id);
		ObjectMapper Obj = new ObjectMapper();
		try{
			if (optional.isPresent()) {
			Category category = optional.get();
			String categoryJson;
			
				categoryJson = Obj.writeValueAsString(category);
				return new ResponseEntity<>(categoryJson, HttpStatus.FOUND);
			
		}
			else {
				return new ResponseEntity<String>( HttpStatus.NO_CONTENT);
			}
		
		} catch (JsonProcessingException e) {
			
			e.printStackTrace();
		}
		return Constants.getResponseEntity(Constants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	
	
	
	
	
	
	
	
	@DeleteMapping("/remove/{id}")
	public ResponseEntity<String> deleteCategoryById(@PathVariable Long id) {

		try {
			Optional<Category> optional = categoryService.getCategory(id);
			if (optional.isPresent()) {
				if (categoryService.hasUsage(optional.get())) {
					return new ResponseEntity<String>("category is not empty...can not be deleted", HttpStatus.OK);
				} else {
					categoryService.deleteCategory(id);
					return new ResponseEntity<String>("Category deleted Suceesfully", HttpStatus.ACCEPTED);
				}
			}
		}

		catch (Exception e) {
			e.printStackTrace();
		}

		return Constants.getResponseEntity(Constants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@DeleteMapping("/remove")
	public ResponseEntity<String> deleteCategory(@RequestBody Category category)
	{
		try {
			Optional<Category> optional = categoryService.getCategory(category.getId());
			if (optional.isPresent()) {
				if (categoryService.hasUsage(optional.get())) {
					return new ResponseEntity<String>("category is not empty...can not be deleted", HttpStatus.OK);
				} else {
					categoryService.deleteCategoryByCategoryObject(optional.get());
					return new ResponseEntity<String>("Category deleted Suceesfully", HttpStatus.ACCEPTED);
				}
			}
		}

		catch (Exception e) {
			e.printStackTrace();
		}

		return Constants.getResponseEntity(Constants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	
	
	
	
	@GetMapping("/total/count")
	public ResponseEntity<String> countAllCategory(){
		Long categoryCount = categoryService.getTotalCount();
		if(categoryCount != 0) {
		return new ResponseEntity<String>(categoryCount.toString(), HttpStatus.OK);
	}
		 return Constants.getResponseEntity(Constants.INCOMPLETE_DETAILS, HttpStatus.NO_CONTENT);
	}
	

}

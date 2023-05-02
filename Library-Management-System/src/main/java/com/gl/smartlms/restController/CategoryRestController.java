package com.gl.smartlms.restController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.gl.smartlms.model.Category;
import com.gl.smartlms.model.Member;
import com.gl.smartlms.service.CategoryService;

import java.util.Optional;

import javax.validation.Valid;

@RestController
@RequestMapping("/category")
public class CategoryRestController {

	@Autowired
	private CategoryService categoryService;

	@RequestMapping(value = "/cadd", method = RequestMethod.POST)
	public ResponseEntity<String> saveCategory(@Valid @RequestBody Category category) {

		category = categoryService.addNew(category);

		return new ResponseEntity<String>("Category Added with type " + category.getName(), HttpStatus.CREATED);

	}
	
	
	@PutMapping("/cupdate")
	public ResponseEntity<String> updateMember(@Valid @RequestBody Category category){
		
		Optional<Category> optional = categoryService.getCategory(category.getId());
		
		if(optional.isPresent()) {
			category.setCreateDate(optional.get().getCreateDate());
			categoryService.save(category);
			
			return new ResponseEntity<String>("Category Updated With Type " + optional.get().getName(),HttpStatus.ACCEPTED);
		}
		
		return  new  ResponseEntity<String>("Member Not Found",HttpStatus.NOT_FOUND);
		
	}
	
	
	
}

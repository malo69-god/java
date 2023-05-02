package com.gl.smartlms.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.gl.smartlms.service.CategoryService;
import com.gl.smartlms.model.Category;



@Controller
@RequestMapping("/category")
public class CategoryController {
	
	@Autowired
	private CategoryService categoryService;
	
	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String addCategoryPage(Model model) {
		model.addAttribute("category", new Category());
		return "/category/form";
	}
	
	
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String saveCategory(@Valid Category category, BindingResult bindingResult, final RedirectAttributes redirectAttributes) {
		if( bindingResult.hasErrors() ) {
			return "/category/form";
		}
		
		if( category.getId() == null ) {
			categoryService.addNew(category);
			redirectAttributes.addFlashAttribute("successMsg", "'" + category.getName() + "' is added as a new category.");
			return "redirect:/category/add";
		} else {
			Category updateCategory = categoryService.save( category );
			redirectAttributes.addFlashAttribute("successMsg", "Changes for '" + category.getName() + "' are saved successfully. ");
			return "redirect:/category/edit/"+updateCategory.getId();
		}
	}
	
	
	@RequestMapping(value = {"/", "/list"}, method = RequestMethod.GET)
	public String showCategoriesPage(Model model) {
		model.addAttribute("categories", categoryService.getAll());
		return "/category/list";
	}
	
	//delete pending

}

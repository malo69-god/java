package com.gl.smartlms.controller;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.gl.smartlms.service.UserService;

import jakarta.servlet.http.HttpSession;

import com.gl.smartlms.model.User;

@Controller
public class UserController {

	@Autowired
	private UserService userService;

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String loginPage() {
		return "/login";
	}

	@PostMapping("/loginuser")
	public String validate(String username, String password, Model model, HttpSession session) {

		User user = userService.getUserValidate(username, password);

		if (user != null) {
			session.setAttribute("user", user);

			return "/welcome";
		} else {

			model.addAttribute("msg", "invalid details");
			return "/login";
		}

	}

}

package com.gl.smartlms.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.gl.smartlms.constants.Constants;
import com.gl.smartlms.service.MemberService;
import com.gl.smartlms.model.Member;

@Controller
@RequestMapping("/member")
public class MemberController {
	
@Autowired
private MemberService memberService;


@ModelAttribute(name="memberTypes")
public List<String> memberTypes(){
	return Constants.MEMBER_TYPES;
}

//add  memeber form 
@GetMapping("/add")
public String addMemeberPage(Model model) {
	model.addAttribute("member", new Member());
	return "/member/form";
}

//save memebr 
@PostMapping(value = "/save")
public String saveMember(@Valid Member member, BindingResult bindingResult, final RedirectAttributes redirectAttributes) {
	if( bindingResult.hasErrors() ) {
		return "/member/form";
	}
	
	if( member.getId() == null ) {
		memberService.addNew(member);
		redirectAttributes.addFlashAttribute("successMsg", "'" + member.getFirstName()+" "+member.getMiddleName() + "' is added as a new member.");
		return "redirect:/member/add";
	} else {
		Member updatedMember = memberService.save( member );
		redirectAttributes.addFlashAttribute("successMsg", "Changes for '" + member.getFirstName()+" "+member.getMiddleName() + "' are saved successfully. ");
		return "redirect:/member/edit/" + updatedMember.getId();
	}
}





//list Member
@RequestMapping(value = {"/", "/list"},  method = RequestMethod.GET)
public String showMembersPage(Model model) {
	model.addAttribute("members", memberService.getAll());
	return "/member/list";
}


//Edit the details of member by id
@RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
public String editMemeberPage(@PathVariable(name = "id") Long id, Model model) {
	Optional<Member> member = memberService.getMember( id );
	if( member.isPresent() ) {
		model.addAttribute("member", member.get());
		return "/member/form";
	} else {
		return "redirect:/member/add";
	}
}


//Delete is pending


}

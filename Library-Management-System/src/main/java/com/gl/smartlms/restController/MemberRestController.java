package com.gl.smartlms.restController;

import java.util.List;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import org.springframework.web.bind.annotation.RestController;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.gl.smartlms.constants.Constants;
import com.gl.smartlms.model.Member;
import com.gl.smartlms.service.MemberService;

@RestController
@RequestMapping("/member")
public class MemberRestController {

	@Autowired
	private MemberService memberService;

	
	
	// ==============================================================
	// Member Count API
	// ==============================================================
	
	@GetMapping("/total/count")
	public ResponseEntity<String> countAllMembers() {
		Long memberCount = memberService.getTotalCount();
		if (memberCount != 0) {
			return new ResponseEntity<String>(memberCount.toString(), HttpStatus.OK);
		}
		return Constants.getResponseEntity(Constants.INCOMPLETE_DETAILS, HttpStatus.NO_CONTENT);
	}

	// ==============================================================
	//	Faculty  Member Count API
	// ==============================================================
	
	@GetMapping("/faculty/count")
	public ResponseEntity<String> countAllFacultyMembers() {
		Long facultyCount = memberService.getFacultyCount();
		if (facultyCount != 0) {
			return new ResponseEntity<String>(facultyCount.toString(), HttpStatus.OK);
		}
		return Constants.getResponseEntity(Constants.INCOMPLETE_DETAILS, HttpStatus.NO_CONTENT);
	}
	
	// ==============================================================
	//	Student Member Count API
	// ==============================================================

	@GetMapping("/student/count")
	public ResponseEntity<String> countAllStudentMembers() {
		Long studentCount = memberService.getStudentsCount();
		if (studentCount != 0) {
			return new ResponseEntity<String>(studentCount.toString(), HttpStatus.OK);
		}
		return Constants.getResponseEntity(Constants.INCOMPLETE_DETAILS, HttpStatus.NO_CONTENT);
	}
	
	// ==============================================================
	//	Add Member API
	// ==============================================================
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public ResponseEntity<String> saveMember(@Valid @RequestBody Member member) {

		member = memberService.addNew(member);
		if(member != null) {
		return new ResponseEntity<String>(
				"Member Added with Name " + member.getFirstName() + "and type " + member.getType(), HttpStatus.CREATED);
		}
		return Constants.getResponseEntity(Constants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	
	// ==============================================================
	//	List Member API(change)url
	// ==============================================================
	@GetMapping("/list")
	public ResponseEntity<List<Member>> showAllMembers() {

		List<Member> list = memberService.getAll();
		try {
			if (list != null) {
				return new ResponseEntity<List<Member>>(list, HttpStatus.FOUND);

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<List<Member>>(HttpStatus.NO_CONTENT);
	}
	
	// ==============================================================
	//	Update Member API(change) url(change)
	// ==============================================================

	@PutMapping("/update")
	public ResponseEntity<String> updateMember(@Valid @RequestBody Member member) {

		Optional<Member> member1 = memberService.getMember(member.getId());

		if (member1.isPresent()) {

			memberService.save(member);

	return new ResponseEntity<String>("Member Updated With Name " + member1.get().getFirstName(),HttpStatus.ACCEPTED);
		}

		return new ResponseEntity<String>("Member Not Found", HttpStatus.NO_CONTENT);

	}

	// ==============================================================
	//	Update Member API(change) 
	// ==============================================================
	
	@GetMapping(value ="/{id}" , produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> findMemberById(@PathVariable Long id) {
		ObjectMapper Obj = new ObjectMapper();
		Optional<Member> optional = memberService.getMember(id);

		try {
			if (optional.isPresent()) {
				Member member = optional.get();
				String memberJson = Obj.writeValueAsString(member);
				return new ResponseEntity<>(memberJson, HttpStatus.FOUND);
			} else {
				return new ResponseEntity<String>(HttpStatus.NO_CONTENT);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return Constants.getResponseEntity(Constants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
	}
}

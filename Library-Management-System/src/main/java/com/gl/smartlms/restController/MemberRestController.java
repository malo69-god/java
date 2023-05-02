package com.gl.smartlms.restController;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;



import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import org.springframework.web.bind.annotation.RestController;

import com.gl.smartlms.constants.Constants;
import com.gl.smartlms.model.Member;
import com.gl.smartlms.service.MemberService;

@RestController
@RequestMapping("/member")
public class MemberRestController {

	@Autowired
	private MemberService memberService;
	
	
	@GetMapping("/total/count")
	public ResponseEntity<String> countAllMembers(){
		Long memberCount = memberService.getTotalCount();
		if(memberCount != 0) {
		return new ResponseEntity<String>(memberCount.toString(), HttpStatus.OK);
	}
		 return Constants.getResponseEntity(Constants.INCOMPLETE_DETAILS, HttpStatus.NO_CONTENT);
	}
	
	
	@GetMapping("/faculty/count")
	public ResponseEntity<String> countAllFacultyMembers(){
		Long facultyCount = memberService.getFacultyCount();
		if(facultyCount != 0) {
		return new ResponseEntity<String>(facultyCount.toString(), HttpStatus.OK);
	}
		 return Constants.getResponseEntity(Constants.INCOMPLETE_DETAILS, HttpStatus.NO_CONTENT);
	}
	
	
	@GetMapping("/student/count")
	public ResponseEntity<String> countAllStudentMembers(){
		Long studentCount = memberService.getStudentsCount();
		if(studentCount != 0) {
		return new ResponseEntity<String>(studentCount.toString(), HttpStatus.OK);
	}
		 return Constants.getResponseEntity(Constants.INCOMPLETE_DETAILS, HttpStatus.NO_CONTENT);
	}
	
	

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public ResponseEntity<String> saveMember(@Valid @RequestBody Member member) {

		member = memberService.addNew(member);

		return new ResponseEntity<String>(
				"Member Added with Name " + member.getFirstName() + "and type " + member.getType(), HttpStatus.CREATED);

	}

	@GetMapping("/mlist")
	public ResponseEntity<List<Member>> showAllMembers() {

		List<Member> list = memberService.getAll();
		if(list != null) {
			return new ResponseEntity<List<Member>>(list, HttpStatus.FOUND);
			
		}
		return new ResponseEntity<List<Member>>(HttpStatus.NO_CONTENT);
	}
	
	@PutMapping("/mupdate")
	public ResponseEntity<String> updateMember(@Valid @RequestBody Member member){
		
		Optional<Member> member1 = memberService.getMember(member.getId());
		
		if(member1.isPresent()) {
	
			memberService.save(member);
			
			return new ResponseEntity<String>("Member Updated With Name " + member1.get().getFirstName(),HttpStatus.ACCEPTED);
		}
		
		return  new  ResponseEntity<String>("Member Not Found",HttpStatus.NOT_FOUND);
		
	}

}

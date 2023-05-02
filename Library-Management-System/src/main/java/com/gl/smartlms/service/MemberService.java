package com.gl.smartlms.service;


import java.util.List;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.gl.smartlms.model.Member;




@Service
public interface MemberService {

	//List Member 
	public List<Member> getAll();
	//Add Member
	public Member addNew(Member member);
	//Save Member
	public Member save(Member member);
	//Find Member by id
	public Optional<Member> getMember(Long id);
	//total member count
	public Long getTotalCount();
	//total Number of Faculty
	public Long getFacultyCount();
	//Total number of Student
	public Long getStudentsCount(); 
	


	
}

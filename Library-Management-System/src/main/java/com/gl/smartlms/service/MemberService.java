package com.gl.smartlms.service;


import java.util.List;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.gl.smartlms.model.Member;




@Service
public interface MemberService {

 
	public List<Member> getAll();

	public Member addNew(Member member);
	
	public Member save(Member member);
	
	public Optional<Member> getMember(Long id);

	public Long getTotalCount();

	public Long getFacultyCount();
	
	public Long getStudentsCount();
	
	 
	public Member getMemberById(Long id);



	
}

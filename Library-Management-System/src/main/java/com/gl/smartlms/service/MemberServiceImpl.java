package com.gl.smartlms.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gl.smartlms.constants.Constants;
import com.gl.smartlms.model.Member;
import com.gl.smartlms.repository.MemberRepository;

@Service
public class MemberServiceImpl implements MemberService {

@Autowired
private MemberRepository memberRepository;



public Long getTotalCount() {
	return memberRepository.count();
}


public Long getFacultyCount() {
	return memberRepository.countByType(Constants.MEMBER_FACULTY);
}

public Long getStudentsCount() {
	return memberRepository.countByType(Constants.MEMBER_STUDENT);
}

	@Override
	public List<Member> getAll() {
		return memberRepository.findAllByOrderByFirstNameAscMiddleNameAsc();

	}

	@Override
	public Member addNew(Member member) {
		member.setJoiningDate( new Date() );
		return memberRepository.save( member );
	}

	@Override
	public Member save(Member member) {
		
		return memberRepository.save(member);
	}

	@Override
	public Optional<Member> getMember(Long id) {
		
		return memberRepository.findById(id);
	}


}

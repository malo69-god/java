package com.gl.smartlms.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gl.smartlms.model.Member;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {

	List<Member> findAllByOrderByFirstNameAscMiddleNameAsc();

	public Long countByType(String type);

	
}

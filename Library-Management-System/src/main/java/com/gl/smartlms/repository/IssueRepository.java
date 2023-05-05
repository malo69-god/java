package com.gl.smartlms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gl.smartlms.model.Issue;

@Repository 
public interface IssueRepository extends JpaRepository<Issue, Long> {

}

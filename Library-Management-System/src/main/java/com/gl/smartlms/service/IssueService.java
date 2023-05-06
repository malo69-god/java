package com.gl.smartlms.service;

import java.util.Date;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.gl.smartlms.model.Issue;

@Service
public interface IssueService {

	public Issue IssueBookToMember(Issue issue);

	public Issue returnBookUpdation(Issue issue);

	public Optional<Issue> getIssueDetailsById(Long id);
	
	public Issue getIssueDetail(Long id);

	public int compareDates(Date expected_date, Date return_date);

}

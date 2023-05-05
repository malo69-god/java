package com.gl.smartlms.service;

import org.springframework.stereotype.Service;

import com.gl.smartlms.model.Issue;

@Service
public interface IssueService {

	public Issue IssueBookToMember(Issue issue);

}

package com.gl.smartlms.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gl.smartlms.constants.Constants;
import com.gl.smartlms.model.Issue;
import com.gl.smartlms.repository.IssueRepository;

@Service
public class IssueSrviceImpl implements IssueService {
	
	
	@Autowired 
	private IssueRepository issueRepository;

	@Override
	public Issue IssueBookToMember(Issue issue) {
		issue.setReturned(Constants.BOOK_NOT_RETURNED);
		
		return issueRepository.save(issue);
	}

}

package com.gl.smartlms.service;




import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;

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

	@Override
	public Issue returnBookUpdation(Issue issue) {
		issue.setReturned(Constants.BOOK_RETURNED);
		return  issueRepository.save(issue);
	}

	@Override
	public Optional<Issue> getIssueDetailsById(Long id) {
		return issueRepository.findById(id);
	}

	@Override
	public Issue getIssueDetail(Long id) {
		return issueRepository.findById(id).get();
	}

	@Override
	public int compareDates(Date expected_date, Date return_date) {
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		 String exp = formatter.format(expected_date);
		 String ret = formatter.format(return_date);
		
		try {
			 Date edate = formatter.parse(exp);
		
			 Date rdate = formatter.parse(ret);
			 return edate.compareTo(rdate);
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return 0;
	}
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
}
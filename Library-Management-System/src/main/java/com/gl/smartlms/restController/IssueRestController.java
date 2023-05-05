package com.gl.smartlms.restController;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gl.smartlms.constants.Constants;
import com.gl.smartlms.model.Book;
import com.gl.smartlms.model.Member;
import com.gl.smartlms.service.BookService;
import com.gl.smartlms.service.IssueService;
import com.gl.smartlms.service.MemberService;
import com.gl.smartlms.model.Issue;

@RestController
@RequestMapping("/issue")
public class IssueRestController {
	
	
	@Autowired
	private BookService bookService;
	@Autowired
	private MemberService memberService;
	@Autowired
	private IssueService issueService;
	
	ObjectMapper Obj = new ObjectMapper();

	@PostMapping("/save")
	public ResponseEntity<String> issueBook(@RequestBody Issue issue) {

		Book book = bookService.getBook(issue.getBook().getId());
		Member member = memberService.getMemberById(issue.getMember().getId());

		try {
			if (book.getStatus() == Constants.BOOK_STATUS_AVAILABLE) {
				book.setStatus(Constants.BOOK_STATUS_ISSUED);
				issue.setBook(book);
				issue.setMember(member);

				bookService.saveBook(book);
				Issue issueDetail = issueService.IssueBookToMember(issue);
				String issueJson = Obj.writeValueAsString(issueDetail);

				return new ResponseEntity<String>(issueJson, null);
			} else {

				return new ResponseEntity<String>("Book is not available", HttpStatus.OK);
			}
		} catch (JsonProcessingException e) {

			e.printStackTrace();
		}

		return Constants.getResponseEntity(Constants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
	}
}

package com.gl.smartlms.service;

import java.util.Date;


import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gl.smartlms.model.Book;
import com.gl.smartlms.repository.BookRepository;
import com.gl.smartlms.constants.*;

@Service
public class BookServiceImpl  implements BookService{

	@Autowired
	private BookRepository bookRepository;
	
	@Override
	public Book getByTag(String tag) {
		return bookRepository.findByTag(tag);
	}

	@Override
	public Book addNewBook(@Valid Book book) {
		book.setCreateDate(new Date());
		book.setStatus( Constants.BOOK_STATUS_AVAILABLE );
		return bookRepository.save(book);
		
	}

	@Override
	public Book saveBook(Book book) {
		return bookRepository.save(book);
	}

}

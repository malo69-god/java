package com.gl.smartlms.service;







import org.springframework.stereotype.Service;

import com.gl.smartlms.model.Book;
 

@Service
public interface BookService {
	
	
	public Book getByTag(String tag);

	public Book addNewBook(Book book);

	public Book saveBook(Book book);

}

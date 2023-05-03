package com.gl.smartlms.repository;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gl.smartlms.model.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

	public Book findByTag(String tag);
	
}

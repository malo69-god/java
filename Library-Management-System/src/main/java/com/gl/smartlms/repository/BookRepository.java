package com.gl.smartlms.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gl.smartlms.model.Book;

public interface BookRepository extends JpaRepository<Book, Long> {

}

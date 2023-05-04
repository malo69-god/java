package com.gl.smartlms.repository;



import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


import com.gl.smartlms.model.Book;
import com.gl.smartlms.model.Category;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

	public Book findByTag(String tag);

	public List<Book> findByAuthors(String authors);

	public List<Book> findByCategory(Category category);
	
}

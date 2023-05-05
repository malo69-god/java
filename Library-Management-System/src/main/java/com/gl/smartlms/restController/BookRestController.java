package com.gl.smartlms.restController;


import java.util.List;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gl.smartlms.constants.Constants;
import com.gl.smartlms.model.Book;
import com.gl.smartlms.model.Category;

import com.gl.smartlms.service.BookService;
import com.gl.smartlms.service.CategoryService;

@RestController
@RequestMapping("/book")
public class BookRestController {
	
	@Autowired
	private BookService bookService;
	
	@Autowired
	private CategoryService categoryService;
	
	
	ObjectMapper Obj = new ObjectMapper();
	
	@GetMapping(value ="/total/count",produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> countAllBooks() {
		Long bookCount = bookService.getTotalCount();
		if (bookCount != 0) {
			return new ResponseEntity<String>(bookCount.toString(), HttpStatus.OK);
		}
		return Constants.getResponseEntity(Constants.INCOMPLETE_DETAILS, HttpStatus.NO_CONTENT);
	}
	
	
	@GetMapping("/list")
	public ResponseEntity<List<Book>> showAllBooks() {

		List<Book> list = bookService.getAll();
		try {
			if (list != null) {
				return new ResponseEntity<List<Book>>(list, HttpStatus.FOUND);

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<List<Book>>(HttpStatus.NO_CONTENT);
	}
	
	@GetMapping("/find")
	public ResponseEntity<List<Book>> getBytitle(@RequestParam String title ) {

		List<Book> list = bookService.getBookWithTitle(title);
		try {
			if (list != null) {
				return new ResponseEntity<List<Book>>(list, HttpStatus.FOUND);

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	
	@GetMapping(value ="/{id}" , produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> findBookById(@PathVariable Long id) {
		ObjectMapper Obj = new ObjectMapper();
		Optional<Book> optional = bookService.getBookById(id);

		try {
			if (optional.isPresent()) {
				Book book = optional.get();
				String bookJson = Obj.writeValueAsString(book);
				return new ResponseEntity<>(bookJson, HttpStatus.FOUND);
			} else {
				return new ResponseEntity<String>(HttpStatus.NO_CONTENT);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return Constants.getResponseEntity(Constants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@GetMapping(value ="/find/{tagname}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> findBookBytag(@PathVariable ("tagname") String tag) {
		ObjectMapper Obj = new ObjectMapper();
		Book book = bookService.getByTag(tag);
		try {
		if(book != null) {
		String bookJson = Obj.writeValueAsString(book);
		return new ResponseEntity<>(bookJson, HttpStatus.FOUND);
			} else {
				return new ResponseEntity<String>(HttpStatus.NO_CONTENT);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return Constants.getResponseEntity(Constants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	
	@GetMapping(value = "/find-by-author/{authors}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> getBooksByAuthor(@PathVariable("authors") String authors) {

		
		List<Book> book = bookService.getByAuthorName(authors);
		try {
			if (book != null) {

				String bookJson = Obj.writeValueAsString(book);

				return new ResponseEntity<String>(bookJson, HttpStatus.OK);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	
	@GetMapping(value = "/find-by-publisher/{publisher}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> getBooksByPublisher(@PathVariable("publisher") String publisher) {

		
		List<Book> book = bookService.getBypublisherName(publisher);
		try {
			if (book != null) {

				String bookJson = Obj.writeValueAsString(book);

				return new ResponseEntity<String>(bookJson, HttpStatus.OK);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	

	@GetMapping(value="/available" ,produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> getAvailable(){
		List<Book> book = bookService.getAvaialbleBooks();
		try {
			if (book != null) {

				String bookJson = Obj.writeValueAsString(book);

				return new ResponseEntity<String>(bookJson, HttpStatus.OK);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<String>("No Books Are Avaiable", HttpStatus.OK);
	}
	

	@GetMapping(value="/category/available/{category_name}" ,produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> getAvailableBooksInCategory(@PathVariable ("category_name") String name){
		
		Optional<Category> category = categoryService.getCategory(name);
		try {if(category.isPresent()) {
		List<Book> list = bookService.geAvailabletByCategory(category.get()); 

			String bookJson = Obj.writeValueAsString(list);
			return new ResponseEntity<String>(bookJson, HttpStatus.FOUND);
		}} catch (JsonProcessingException e) {
		
			e.printStackTrace();
		}	
		return new ResponseEntity<String>("No Books Are Avaiable", HttpStatus.OK);
	}
	
	
	
	
	
	

	@GetMapping(value = "/find-books/{ids}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> findBooksWithIdList(@PathVariable List<Long> ids) {
		List<Book> list = bookService.getBooksByIdList(ids);
		try {
			String bookJson = Obj.writeValueAsString(list);
			return new ResponseEntity<String>(bookJson, HttpStatus.FOUND);
		} catch (JsonProcessingException e) {
			
			e.printStackTrace();
		}
		return new ResponseEntity<>(HttpStatus.OK);
	}

	
	@GetMapping(value = "/list-by-category/{category_id}" , produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> findBooksByCategory(@PathVariable("category_id") Long id) {
		String bookJson;
		Optional<Category> category = categoryService.getCategory(id);
		try {
			if (category.isPresent()) {
				List<Book> list = bookService.getByCategory(category.get());

				bookJson = Obj.writeValueAsString(list);
				return new ResponseEntity<String>(bookJson, HttpStatus.FOUND);
			} else {
				return new ResponseEntity<String>("Categorgy not Exist", HttpStatus.OK);

			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return Constants.getResponseEntity(Constants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);

	}
	
	
	@GetMapping(value = "/list-by-category" , produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> findBooksByCategory(@RequestParam("category_name") String name) {
		String bookJson;
		Optional<Category> category = categoryService.getCategory(name);
		try {
			if (category.isPresent()) {
				List<Book> list = bookService.getByCategory(category.get());
				bookJson = Obj.writeValueAsString(list);
				return new ResponseEntity<String>(bookJson, HttpStatus.FOUND);
			} else {
				return new ResponseEntity<String>("Categorgy not Exist", HttpStatus.OK);

			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return Constants.getResponseEntity(Constants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);

	}
	
	
	@PostMapping("/add/{id}")
	public ResponseEntity<String>  addBook(@RequestBody Book book,@PathVariable ("id") Long id ){
		Optional<Category> optional = categoryService.getCategory(id);
		if(optional.isPresent()) {
			book.setCategory(optional.get());
			
			if( bookService.getByTag(book.getTag()) != null ) {
				
				return new ResponseEntity<String>("tag already exist" ,HttpStatus.NOT_ACCEPTABLE);
			} else {
				bookService.addNewBook(book);
				
				return new ResponseEntity<String>("Book get Added with Title " + book.getTitle() +" and Category" + optional.get().getName() ,HttpStatus.CREATED);
			}
			
		}
		
		 return new ResponseEntity<String>("Category Not Available" ,HttpStatus.OK);
		
	}
	
	
	//update book with same category
	@PutMapping("/update")
	public ResponseEntity<String> updateBook(@RequestBody Book book){
		Optional<Book> optional = bookService.getBookById(book.getId());
		if(optional.isPresent()) {
			Optional<Category> category = categoryService.getCategory(optional.get().getCategory().getId());
			
			book.setCategory(category.get());
			book.setCreateDate(optional.get().getCreateDate());
			book.setStatus(optional.get().getStatus());
			bookService.saveBook(book);
			return new ResponseEntity<String>("Succesfully Updated Book Details", HttpStatus.ACCEPTED);
		}
		return new ResponseEntity<String>("Details Not updated" , HttpStatus.NOT_ACCEPTABLE);
		
	}
	
	
	//update book as well as  Change Category
	@PutMapping(value ="/update/{id}" , produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> updateBook(@RequestBody Book book,@PathVariable Long id){
		ObjectMapper Obj = new ObjectMapper();
		Optional<Book> optional = bookService.getBookById(book.getId());
		if(optional.isPresent()) {
			Optional<Category> category = categoryService.getCategory(id);
			book.setCategory(category.get());
			book.setCreateDate(optional.get().getCreateDate());
			book.setStatus(optional.get().getStatus());
			bookService.saveBook(book);
			try {
				String bookJson = Obj.writeValueAsString(book);
				return new ResponseEntity<String>("Succesfully Updated Book Details category of type"+category.get().getName()+" "+bookJson, HttpStatus.ACCEPTED);
			} catch (JsonProcessingException e) {
				
				e.printStackTrace();
			}
			
		}
		return new ResponseEntity<String>("Details Not updated" , HttpStatus.NOT_ACCEPTABLE);
		
	}
	
	



}

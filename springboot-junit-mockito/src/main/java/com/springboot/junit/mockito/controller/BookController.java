package com.springboot.junit.mockito.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.junit.mockito.entity.Book;
import com.springboot.junit.mockito.repository.BookRepository;

@RestController
@RequestMapping(value = "/book")
public class BookController {
	
	@Autowired
	private BookRepository bookRepository;
	
	 @GetMapping
	 public List<Book> getAllBookRecords(){
		return bookRepository.findAll();
		 
	 }
	 
	 @GetMapping(value = "{bookID}")
	 public Book getBookById(@PathVariable(value = "bookId") Long bookId) {
		return bookRepository.findById(bookId).get();
		 
	 }

	 @PostMapping()
	 public Book createBookRecord(@RequestBody Book bookRecord) {
		return bookRepository.save(bookRecord);
		 
	 }
	 
	 @PutMapping
	 public Book updateBookRecord(@RequestBody Book bookRecord) throws Exception {
		 
		 if(bookRecord == null || bookRecord.getBookId() == null) {
			 throw new Exception("BookRecord or ID must not be null!");
		 }
		 
		 Optional<Book> optionalBook = bookRepository.findById(bookRecord.getBookId());
		 if(!optionalBook.isPresent()) {
			 throw new Exception("Book with ID: " +bookRecord.getBookId()+ "doestn't exist");
		 
		 }
		
		 Book existingBookRecord = optionalBook.get();
		 existingBookRecord.setName(bookRecord.getName());
		 existingBookRecord.setSummary(bookRecord.getName());
		 existingBookRecord.setRating(bookRecord.getRating());
		return bookRepository.save(existingBookRecord);
		 
	 }
	 
	 
	 //@DeleteMapping
	// public 
}

package com.springboot.junit.mockito;


import static org.hamcrest.CoreMatchers.hasItems;
import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.springboot.junit.mockito.controller.BookController;
import com.springboot.junit.mockito.entity.Book;
import com.springboot.junit.mockito.repository.BookRepository;

import ch.qos.logback.core.net.ObjectWriter;



@RunWith(MockitoJUnitRunner.class)
public class BookControllerTest {
	
	private MockMvc mockMvc;
	
	ObjectMapper objectMapper = new ObjectMapper();
	ObjectWriter objectWriter = (ObjectWriter) objectMapper.writer();
	
	@Mock
	private BookRepository bookRepository;
	
	@InjectMocks
	private BookController bookController;
	
	Book record1 = new Book((long) 1, "Atomic Habits", "How to build better habits", 5);
	Book record2 = new Book((long) 2, "Thinking fast and slow", "How to create good mental models", 4);
	Book record3 = new Book((long) 3, "Grokking Algorithms", "Lean algorithms in fun way", 5);
	
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		this.mockMvc=MockMvcBuilders.standaloneSetup(bookController).build();
	}

	
	@Test
	public void getAllRecords_success() throws Exception {
		
		List<Book> records = new ArrayList<>(Arrays.asList(record1,record2,record3));
		Mockito.when(bookRepository.findAll()).thenReturn(records);
		
		mockMvc.perform(MockMvcRequestBuilders
				.get("/book")
				.contentType(MediaType.APPLICATION_JSON))
		        .andExpect(status().isOk())
		        .andExpect(MockMvcResultMatchers.jsonPath("$", hasItems(3)))
		        .andExpect(jsonPath("$[2].name", is("Grokking Algorithms")));
		        		
				
		
	}
}

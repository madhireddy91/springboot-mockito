package com.springboot.junit.mockito.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.junit.mockito.entity.Book;

public interface BookRepository extends JpaRepository<Book, Long>{

}

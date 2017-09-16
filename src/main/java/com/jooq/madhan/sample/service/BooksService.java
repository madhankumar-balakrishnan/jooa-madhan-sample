package com.jooq.madhan.sample.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jooq.madhan.sample.model.AuthorBookModel;
import com.jooq.madhan.sample.model.BookModel;
import com.jooq.madhan.sample.repository.BookRepository;

@Service
@Transactional
public class BooksService {
	
	@Autowired
	private BookRepository bookRepository;
	
	public List<BookModel> getAllBooks(){
		return bookRepository.getAllBooks();
	}
	
	public List<AuthorBookModel> getAuthorDetailByBookId(Integer id){
		return bookRepository.getAuthorDetailByBookId(id);
	}
}

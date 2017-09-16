package com.jooq.madhan.sample.controller;

import java.util.List;
import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.jooq.madhan.sample.exception.JooqHttpException;
import com.jooq.madhan.sample.model.AuthorBookModel;
import com.jooq.madhan.sample.model.BookModel;
import com.jooq.madhan.sample.service.BooksService;

@RestController
@RequestMapping("/v1")
public class BookController {

	public static final Logger logger = LoggerFactory.getLogger(BookController.class);
	
	@Autowired
	BooksService bookService;
	
	@RequestMapping(value = "/books", method = RequestMethod.GET)
    public ResponseEntity<List<BookModel>> getBooks() {
        logger.info("Fetching all books");
        List<BookModel> books = bookService.getAllBooks();
        if (Objects.isNull(books)) {
            logger.error("Books Not Found");
            return new ResponseEntity(new JooqHttpException("CUSTOM_CODE_101",String.valueOf(HttpStatus.NOT_FOUND.value())), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(books, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/book/{id}", method = RequestMethod.GET)
    public ResponseEntity<List<AuthorBookModel>> getBookById(@PathVariable("id") Integer id) {
        logger.info("Fetching book by id {}", id);
        List<AuthorBookModel> authorBookModels = bookService.getAuthorDetailByBookId(id);
        if (Objects.isNull(authorBookModels)) {
            logger.error("Books Not Found");
            return new ResponseEntity(new JooqHttpException("CUSTOM_CODE_101",String.valueOf(HttpStatus.NOT_FOUND.value())), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(authorBookModels, HttpStatus.OK);
	}
}

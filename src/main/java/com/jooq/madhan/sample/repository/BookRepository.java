package com.jooq.madhan.sample.repository;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.jooq.DSLContext;
import org.jooq.Record3;
import org.jooq.Result;
import org.jooq.SelectSeekStep2;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jooq.madhan.sample.domain.tables.Author;
import com.jooq.madhan.sample.domain.tables.AuthorBook;
import com.jooq.madhan.sample.domain.tables.Book;
import com.jooq.madhan.sample.domain.tables.records.BookRecord;
import com.jooq.madhan.sample.model.AuthorBookModel;
import com.jooq.madhan.sample.model.BookModel;

@Repository
public class BookRepository {

	public static final Logger logger = LoggerFactory.getLogger(BookRepository.class);
	
    @Autowired
    private DSLContext dsl;
    
    private Author author = Author.AUTHOR;

    private Book book = Book.BOOK;
    
    private AuthorBook authorBook = AuthorBook.AUTHOR_BOOK; 
    
	public List<BookModel> getAllBooks(){
		Map<String, Result<BookRecord>> bookRecordMap = dsl.selectFrom(book).orderBy(book.TITLE).fetchGroups(book.TITLE);
		List<BookModel> bookModels = bookRecordMap.values().stream().map(result -> 
			result.map(record -> {
				logger.info("BOOK ID: {} and TITLE: {}",record.getId(),record.getTitle());
				return new BookModel(record.getId(),record.getTitle());
			}
			)
		).flatMap(List::stream).map(object -> (BookModel)object).collect(Collectors.toList());
		logger.info("*********** Book models: {}",bookModels);
		return bookModels;
	}
	
	public List<AuthorBookModel> getAuthorDetailByBookId(Integer id){
		//select a.first_name,a.last_name from author a, book b, author_book ab where a.id = ab.author_id and b.id=ab.book_id and b.id =3;
		SelectSeekStep2<Record3<String, String, String>, String, String> selectSeekStep2 = dsl.select(author.FIRST_NAME,author.LAST_NAME,book.TITLE).
																	from(author).
																	join(authorBook).
																	on(author.ID.eq(authorBook.AUTHOR_ID)).
																	join(book).on(book.ID.eq(authorBook.BOOK_ID)).
																	and(book.ID.eq(id)).orderBy(author.FIRST_NAME,author.LAST_NAME);
		
		Result<?> results = selectSeekStep2.fetch();
		logger.info("Author Details By Book Id: {}",selectSeekStep2.getSQL());
		return results.stream().
				map(record -> new AuthorBookModel(record.getValue(author.FIRST_NAME),record.getValue(author.LAST_NAME),record.getValue(book.TITLE))).
				map(object -> (AuthorBookModel)object).collect(Collectors.toList());
	}
}

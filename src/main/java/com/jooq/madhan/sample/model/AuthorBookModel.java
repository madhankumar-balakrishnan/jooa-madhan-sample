package com.jooq.madhan.sample.model;

public class AuthorBookModel {

	private String authorFirstName;
	private String authorLastName;
	private String bookTitle;
	
	public AuthorBookModel() {
		super();
	}

	public AuthorBookModel(String authorFirstName, String authorLastName,
			String bookTitle) {
		super();
		this.authorFirstName = authorFirstName;
		this.authorLastName = authorLastName;
		this.bookTitle = bookTitle;
	}

	public String getAuthorFirstName() {
		return authorFirstName;
	}

	public void setAuthorFirstName(String authorFirstName) {
		this.authorFirstName = authorFirstName;
	}

	public String getAuthorLastName() {
		return authorLastName;
	}

	public void setAuthorLastName(String authorLastName) {
		this.authorLastName = authorLastName;
	}

	public String getBookTitle() {
		return bookTitle;
	}

	public void setBookTitle(String bookTitle) {
		this.bookTitle = bookTitle;
	}
}

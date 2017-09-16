package com.jooq.madhan.sample.model;

public class BookModel {

	private Integer id;
	private String title;

	
	public BookModel() {
		super();
	}

	public BookModel(Integer id, String title) {
		super();
		this.id = id;
		this.title = title;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
}

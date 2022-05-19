package com.kh.uml.association.aggregation;

/**
 * Aggregation 집약관계
 * - Book 객체와 BookInfo 객체의 생명주기는 독립적이다.
 * - Book 객체가 제거되어도, BookInfo 객체는 유지될 수 있다.
 *
 */
public class Book {

	private BookInfo bookInfo;
	private long id; // 관리번호
	private boolean isRented; 
	
	public Book(long id, BookInfo bookInfo) {
		this.id = id;
		this.bookInfo = bookInfo;
	}
	
	public BookInfo getBookInfo() {
		return bookInfo;
	}
}

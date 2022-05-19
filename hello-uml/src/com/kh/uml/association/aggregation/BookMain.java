package com.kh.uml.association.aggregation;

import java.util.ArrayList;
import java.util.List;

public class BookMain {

	public static void main(String[] args) {
		
		BookInfo bookInfo = new BookInfo("마지막 몰입", "짐 퀵", "비즈니스북스");
		List<Book> list = new ArrayList<>();
		list.add(new Book(1L, bookInfo));
		list.add(new Book(1L, bookInfo));
		list.add(new Book(1L, bookInfo));
		System.out.println(list);
		
		list.remove(2); // 셋째 Book 객체 제거, BookInfo 객체는 제거되지 ㅇ낳는다.

		System.out.println(list);
	}

}

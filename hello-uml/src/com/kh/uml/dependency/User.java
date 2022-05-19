package com.kh.uml.dependency;

/**
 * 
 * Dependency 의존관계
 * 
 * User#buy 메소드에서 지역변수(매개변수)로 Item을 참조한다.
 *
 */
public class User {
	
	private String userName;
	
	public void buy(Item item) {
		System.out.println(userName + "이 " + item.getName() + "을 " + item.getPrice() + "를 구매한다...");
	}
}

package com.kh.uml.association.composition;

import java.util.ArrayList;
import java.util.List;


/**
 * Composition 합성관계
 * House - Room
 * 
 * - House가 Room 객체의 생명주기를 결정한다.
 * - House 객체가 제거되면 Room 객체도 제거된다.
 * 
 */
public class House {
	
	private String address;
	private List<Room> rooms;
	
	public House(int roomCnt) {
		rooms = new ArrayList<>();
		for(int i = 0; i < roomCnt; i++) {
			rooms.add(new Room());
		}
	}
	
	
	
}

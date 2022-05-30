package com.kh.ajax.celeb.manager;

import java.util.ArrayList;
import java.util.List;

import com.kh.ajax.celeb.dto.Celeb;
import com.kh.ajax.celeb.dto.CelebType;

/**
 * singletone 프로그램 운영 중 단 하나의 객체만 사용
 *
 */
public class CelebManager {
	private static CelebManager instance;
	private List<Celeb> celebList = new ArrayList<>();
	
	private CelebManager() {
		celebList.add(new Celeb(1, "daft punk", CelebType.SINGER, "daftpunk.jpg"));
		celebList.add(new Celeb(2, "황제성", CelebType.COMEDIAN, "hwang.jpg"));
		celebList.add(new Celeb(3, "julia roberts", CelebType.ACTOR, "juliaRoberts.jpg"));
		celebList.add(new Celeb(4, "matt damon", CelebType.ACTOR, "mattDamon.jpg"));
		celebList.add(new Celeb(5, "유재석", CelebType.ENTERTAINER, "d9bc32d2ed4fb5c5.jpg"));
		celebList.add(new Celeb(6, "김고은", CelebType.ACTOR, "37664c7ef6d45741.jpg"));
		celebList.add(new Celeb(7, "양세찬", CelebType.ENTERTAINER, "yang_se_chan.jpg"));
		celebList.add(new Celeb(8, "박보검", CelebType.ACTOR, "parkBogum.jpg"));
	}
	
	public static CelebManager getInstance() {
		if(instance == null) {
			instance = new CelebManager();
		}
			return instance;
	}

	public List<Celeb> getCelebList() {
		return celebList;
	}
	
	
}

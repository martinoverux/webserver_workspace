package kh.java.pattern.strategy;

public class Main {
	public static void main(String[] args) {
		Main main = new Main();
		main.test1();
	}
	
	private void test1() {
		Person hyeonwoo = new Person("현우", new Dog("구리구리")); // 반려견
		Person ara = new Person("아라", new Dog("이슬")); // 반려견
		
		hyeonwoo.playWithPet();
		ara.playWithPet();
		
		Person gd = new Person("홍길동", new Snake("드래곤"));
		gd.playWithPet();
	}
}

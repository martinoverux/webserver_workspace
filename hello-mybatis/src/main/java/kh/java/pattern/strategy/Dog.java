package kh.java.pattern.strategy;

public class Dog extends Pet {

	public Dog(String name) {
		super(name);
	}

	@Override
	public String getReply() {
		return "멍멍";
	}
}

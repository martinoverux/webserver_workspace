package com.kh.person.model.dto;

public class Person {

	private String name;
	private char gender;
	private int age;
	private boolean married;
	
	public Person() {
		super();
	}

	public Person(String name, char gender, int age, boolean married) {
		super();
		this.name = name;
		this.gender = gender;
		this.age = age;
		this.married = married;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public char getGender() {
		return gender;
	}

	public void setGender(char gender) {
		this.gender = gender;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public boolean isMarried() {
		return married;
	}

	public void setMarried(boolean married) {
		this.married = married;
	}

	@Override
	public String toString() {
		return "Person [name=" + name + ", gender=" + gender + ", age=" + age + ", married=" + married + "]";
	}
}
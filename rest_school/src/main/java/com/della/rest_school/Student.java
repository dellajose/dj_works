package com.della.rest_school;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Student {
	private int Id;
	private String LastName;
	private String FirstName;
	private int age;
	public int getId() {
		return Id;
	}
	
	public void setId(int id) {
		Id = id;
	}
	public String getLastName() {
		return LastName;
	}
	public void setLastName(String lastName) {
		LastName = lastName;
	}
	public String getFirstName() {
		return FirstName;
	}
	public void setFirstName(String firstName) {
		FirstName = firstName;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
}

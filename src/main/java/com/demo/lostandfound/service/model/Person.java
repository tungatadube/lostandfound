package com.demo.lostandfound.service.model;

import javax.persistence.*;

@Entity
public class Person {
	@Id()
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private long  id;

	@Column(name="name")
	private String firstName;
	private String surname;
	@Column(name="phone_number")
	private String phoneNumber;
	private String province;
	private String address;




	public Person() {
	}

	public Person(String firstName, String surname, String phoneNumber, String province, String address) {
		this.firstName = firstName;
		this.surname = surname;
		this.phoneNumber = phoneNumber;
		this.province = province;
		this.address = address;
	}

	public Long getId(){
		return id;
	}
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}


}


package com.demo.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.Size;

@Entity
public class User {

    @Id
    @GeneratedValue
    Integer id;

    @Size(min = 2, message = "name should be more than 2 chars")
    String name;

    Date dob;

    @OneToMany(mappedBy = "user")
    List<Post> posts;

    public User() {

    }

    public User(Integer id, String name, Date dob) {
	super();
	this.id = id;
	this.name = name;
	this.dob = dob;
    }

    public Integer getId() {
	return id;
    }

    public void setId(Integer id) {
	this.id = id;
    }

    public String getName() {
	return name;
    }

    public void setName(String name) {
	this.name = name;
    }

    public Date getDob() {
	return dob;
    }

    public void setDob(Date dob) {
	this.dob = dob;
    }

}

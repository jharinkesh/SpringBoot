package com.demo.controllers;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.demo.common.UserNotFoundException;
import com.demo.dao.UserDaoService;
import com.demo.entity.User;

@RestController
public class UserResource {

    @Autowired
    UserDaoService service;

    @GetMapping("/users")
    public List<User> getAllUsers() {
	return service.findAll();
    }

    @GetMapping("/users/{userId}")
    public User getUser(@PathVariable Integer userId) {
	User user = service.findOne(userId);
	if (user == null)
	    throw new UserNotFoundException("id: " + userId);
	return user;
    }

    @PostMapping("/users")
    public ResponseEntity<User> saveUser(@Valid @RequestBody User user) {
	user = service.save(user);
	URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(user.getId())
		.toUri();
	return ResponseEntity.created(location).build();
    }

    @DeleteMapping("/users/{userId}")
    public void deleteUser(@PathVariable Integer userId) {
	User user = service.delete(userId);
	if (user == null)
	    throw new UserNotFoundException("id: " + userId);
    }
}

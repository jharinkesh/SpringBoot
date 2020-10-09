package com.demo.controllers;

import java.net.URI;
import java.util.List;
import java.util.Optional;

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
import com.demo.dao.UserRepository;
import com.demo.entity.User;

@RestController
public class UserJPAResource {

    @Autowired
    private UserRepository repository;

    @GetMapping("/jpa/users")
    public List<User> getAllUsers() {
	return repository.findAll();
    }

    @GetMapping("/jpa/users/{userId}")
    public User getUser(@PathVariable Integer userId) {
	Optional<User> user = repository.findById((long) userId);
	if (user.isEmpty())
	    throw new UserNotFoundException("id: " + userId);
	return user.get();
    }

    @PostMapping("/jpa/users")
    public ResponseEntity<User> saveUser(@Valid @RequestBody User user) {
	user = repository.save(user);
	URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(user.getId())
		.toUri();
	return ResponseEntity.created(location).build();
    }

    @DeleteMapping("/jpa/users/{userId}")
    public void deleteUser(@PathVariable Integer userId) {
	repository.deleteById((long) userId);
    }
}

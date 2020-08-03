package com.demo.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Component;

import com.demo.entity.User;

@Component
public class UserDaoService {
    private static List<User> users = new ArrayList<>();
    private static int userCount = 3;
    static {
	users.add(new User(1, "ram", new Date()));
	users.add(new User(2, "shyam", new Date()));
	users.add(new User(3, "mohan", new Date()));
    }

    public List<User> findAll() {
	return this.users;
    }

    public User save(User user) {
	if (user.getId() == null)
	    user.setId(++userCount);
	users.add(user);
	return user;
    }

    public User findOne(Integer id) {
	for (User user : users) {
	    if (user.getId().equals(id))
		return user;
	}
	return null;
    }

    public User delete(Integer id) {
	for (User user : users) {
	    if (user.getId().equals(id)) {
		users.remove(user);
		return user;
	    }
	}
	return null;
    }
}

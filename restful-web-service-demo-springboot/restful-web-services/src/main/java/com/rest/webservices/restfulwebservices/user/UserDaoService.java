package com.rest.webservices.restfulwebservices.user;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Component;

// @Component->to scan automatically by spring 
@Component
public class UserDaoService {

	private static List<User> users = new ArrayList<>();
	private static int count = 3;
	static {
		users.add(new User(1, "Adam", new Date(), new ArrayList<>()));
		users.add(new User(2, "Eve", new Date(), new ArrayList<>()));
		users.add(new User(3, "Jack", new Date(), new ArrayList<>()));
	}

	public List<User> findAll() {
		// TODO Auto-generated method stub
		return users;
	}

	public User save(User user) {
		// TODO Auto-generated method stub
		if (user.getId() == null) {
			user.setId(++count);
		}
		users.add(user);
		return user;
	}

	public User findOne(int id) {
		// TODO Auto-generated method stub
		User user = users.stream().filter(x -> x.getId() == id).findFirst().orElse(null);
		if (user == null) {
			throw new UserNotFoundException("id - " + id);
		}
		return user;
	}

	public User deleteById(int id) {
		// TODO Auto-generated method stub
		User user = users.stream().filter(x -> x.getId() == id).findFirst().orElse(null);
		if (user == null) {
			throw new UserNotFoundException("id - " + id);
		} else {
			users.remove(user);
		}
		return user;
	}
}
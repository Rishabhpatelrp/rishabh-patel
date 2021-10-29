package com.rest.webservices.restfulwebservices.user;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
public class UserResource {

	@Autowired
	private UserDaoService userDaoService;

	@GetMapping(path = "users")
	public List<User> retriveAllUsers() {
		// TODO Auto-generated method stub
		return userDaoService.findAll();
	}

	@GetMapping(path = "users/{id}")
	public EntityModel<User> retriveOneUser(@PathVariable int id) {
		// TODO Auto-generated method stub
		 User findOne = userDaoService.findOne(id);
		 EntityModel<User> entityModel = EntityModel.of(findOne);
		 //extra link builder, done by HATEOSE fw added in POM.xml
		 WebMvcLinkBuilder builder = linkTo(methodOn(this.getClass()).retriveAllUsers());
		 entityModel.add(builder.withRel("rel-users"));
		return entityModel;
	}

	@PostMapping(path = "users")
	public ResponseEntity<Object> saveUser(@Valid @RequestBody User user) {
		// TODO Auto-generated method stub
		User savedUser = userDaoService.save(user);
		//Build location of created user
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedUser.getId())
				.toUri();
		//send back created response using response entity
		return ResponseEntity.created(location).build();

	}
	
	@DeleteMapping(path = "users/{id}")
	public User deleteUserById(@PathVariable int id) {
		// TODO Auto-generated method stub
		
		return userDaoService.deleteById(id);
	}
}

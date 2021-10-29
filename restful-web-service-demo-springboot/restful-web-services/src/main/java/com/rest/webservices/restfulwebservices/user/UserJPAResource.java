package com.rest.webservices.restfulwebservices.user;

import java.net.URI;
import java.util.List;
import java.util.Optional;

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
public class UserJPAResource {

	@Autowired
	private UserDaoService userDaoService;

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PostRepository postRepository;
	
	@GetMapping(path = "jpa/users")
	public List<User> retriveAllUsers() {
		// TODO Auto-generated method stub
		return userRepository.findAll();
	}

	@GetMapping(path = "jpa/users/{id}")
	public EntityModel<User> retriveOneUser(@PathVariable int id) {
		// TODO Auto-generated method stub
		 Optional<User> findOne = userRepository.findById(id);
		 
		 EntityModel<User> entityModel = EntityModel.of(findOne.get());
		 //extra link builder, done by HATEOSE fw added in POM.xml
		 WebMvcLinkBuilder builder = linkTo(methodOn(this.getClass()).retriveAllUsers());
		 entityModel.add(builder.withRel("rel-users"));
		return entityModel;
	}

	@PostMapping(path = "jpa/users")
	public ResponseEntity<Object> saveUser(@Valid @RequestBody User user) {
		// TODO Auto-generated method stub
		User savedUser = userRepository.save(user);
		//Build location of created user
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedUser.getId())
				.toUri();
		//send back created response using response entity
		return ResponseEntity.created(location).build();

	}
	
	@DeleteMapping(path = "jpa/users/{id}")
	public void deleteUserById(@PathVariable int id) {
		// TODO Auto-generated method stub
		
		userRepository.deleteById(id);
	}
	
	@GetMapping(path = "jpa/users/{id}/post")
	public List<Post> retriveAllUsers(@PathVariable int id) {
		// TODO Auto-generated method stub
		Optional<User> user = userRepository.findById(id);
		if(!user.isPresent()) {
			throw new UserNotFoundException("id : "+id);
		}
		return user.get().getPosts();
		
	}
	
	/**
	 * Create new post
	 * @param id
	 * @param post
	 * @return
	 */
	@PostMapping(path = "jpa/users/{id}/post")
	public ResponseEntity<Object> createPost(@PathVariable int id, @RequestBody Post post) {
		// TODO Auto-generated method stub
		Optional<User> optionalUser = userRepository.findById(id);
		if(!optionalUser.isPresent()) {
			throw new UserNotFoundException("id : "+id);
		}
		User user = optionalUser.get();
		
		post.setUser(user);
		postRepository.save(post);
		//Build location of created user
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().build()
				.toUri();
		//send back created response using response entity
		return ResponseEntity.created(location).build();

	}
}

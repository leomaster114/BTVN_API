package com.hoanv.SpringBootApi;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/")
public class RestApiController {

	@Autowired
	UserRepository repository;

	@PostConstruct
	public void init() {

	}

	@GetMapping("/users")
	public List<User> getPeople() {
		return repository.getAllUser();
	}

	@GetMapping("/users/{userId}")
	public User getPerson(@PathVariable(name = "userId") Integer userId) {
		return repository.getUserById(userId);

	}

	@PutMapping("/users/{userId}")
	public ResponseEntity<?> editPerson(@PathVariable(name = "userId") Integer userId, @RequestBody User user) {
		if (repository.getUserById(userId) == null) {
			return new ResponseEntity<String>("Unable to update as User id: " + userId + " not found!",
					HttpStatus.NOT_FOUND);
		}
		repository.update(user);
		return new ResponseEntity<User>(user, HttpStatus.OK);
	}
//
//	@DeleteMapping("/person/{personId}")
//	public ResponseEntity deletePerson(@PathVariable(name = "personId") Integer personId) {
//		people.remove(personId.intValue());
//		return ResponseEntity.ok().build();
//	}

	@PostMapping("/users")
	public ResponseEntity<String> addPerson(@RequestBody User user1) {
		User user = repository.findUserByName(user1.getUsername());
		if (user != null) {
			return new ResponseEntity<String>("username is existed, please select other username!", HttpStatus.IM_USED);
		}
		repository.addUser(user1);
		return ResponseEntity.status(HttpStatus.CREATED).build();

	}
}

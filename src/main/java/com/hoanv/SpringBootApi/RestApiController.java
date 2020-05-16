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
@RequestMapping("/api/demo")
public class RestApiController {

	@Autowired
	UserRepository repository;

	@PostConstruct
	public void init() {

	}

	@GetMapping("/person")
	public List<User> getPeople() {
		return repository.getAllUser();
	}

	@GetMapping("/person/{personId}")
	public User getPerson(@PathVariable(name = "personId") Integer personId) {
		return repository.getUserById(personId);

	}

	@PutMapping("/person/{personId}")
	public ResponseEntity<?> editPerson(@PathVariable(name = "personId") Integer personId, @RequestBody User person) {
		if (repository.getUserById(personId) == null) {
			return new ResponseEntity<String>("Unable to update as User id: " + personId + " not found!",
					HttpStatus.NOT_FOUND);
		}
		repository.update(person);
		return new ResponseEntity<User>(person, HttpStatus.OK);
	}
//
//	@DeleteMapping("/person/{personId}")
//	public ResponseEntity deletePerson(@PathVariable(name = "personId") Integer personId) {
//		people.remove(personId.intValue());
//		return ResponseEntity.ok().build();
//	}

	@PostMapping("/person")
	public ResponseEntity<String> addPerson(@RequestBody User person) {
		User user = repository.findUserByName(person.getUsername());
		if (user != null) {
			return new ResponseEntity<String>("username is existed, please select other username!", HttpStatus.IM_USED);
		}
		repository.addUser(person);
		return ResponseEntity.status(HttpStatus.CREATED).build();

	}
}

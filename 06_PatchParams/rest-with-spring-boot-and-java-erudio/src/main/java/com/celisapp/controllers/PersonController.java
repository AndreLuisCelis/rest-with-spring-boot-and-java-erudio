package com.celisapp.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.celisapp.data.vo.v1.PersonVO;
import com.celisapp.data.vo.v2.PersonVOV2;
import com.celisapp.service.PersonService;

@RestController
@RequestMapping("api/person")
public class PersonController {

	@Autowired
	PersonService personService;

	@GetMapping(produces =  { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<List<PersonVO>> findAll() {
		var persons = personService.findAll();
		return ResponseEntity.ok(persons);
	}

	@GetMapping(value = "/{id}", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<PersonVO> findById(@PathVariable(value = "id") Long id) {
		var person = personService.findById(id);
		return ResponseEntity.ok(person);
	}

	@PostMapping(produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE }, consumes = {
			MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<PersonVO> createPerson(@RequestBody() PersonVO person) {
		return ResponseEntity.ok(personService.createPerson(person));
	}

	@PutMapping(produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE }, consumes = {
			MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<PersonVO> updatePerson(@RequestBody() PersonVO person) {
		return ResponseEntity.ok(personService.updatePerson(person));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> deletePerson(@PathVariable(value = "id") Long id) {
		personService.deletePerson(id);
		return ResponseEntity.noContent().build();
	}

	/* V2 */

	@GetMapping(value = "/v2", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<List<PersonVOV2>> findAllV2() {
		var persons = personService.findAllV2();
		return ResponseEntity.ok(persons);
	}

	@PostMapping(value = "v2", produces = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE }, consumes = { MediaType.APPLICATION_JSON_VALUE,
					MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<PersonVOV2> createPersonV2(@RequestBody() PersonVOV2 person) {
		return ResponseEntity.ok(personService.createPersonV2(person));
	}
}

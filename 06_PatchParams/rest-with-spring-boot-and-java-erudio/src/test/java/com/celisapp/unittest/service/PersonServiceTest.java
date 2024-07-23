package com.celisapp.unittest.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.hateoas.RepresentationModel;

import com.celisapp.data.vo.v1.PersonVO;
import com.celisapp.exceptions.RequiredObjectIsNullException;
import com.celisapp.model.Person;
import com.celisapp.repositories.PersonRepository;
import com.celisapp.service.PersonService;
import com.celisapp.unittest.mapper.mocks.MockPerson;


@TestInstance(Lifecycle.PER_CLASS)
@ExtendWith(MockitoExtension.class)
class PersonServiceTest {
	
	MockPerson input;
	
	@InjectMocks
	private PersonService service;
	
	@Mock
	private PersonRepository repository;
	
	@BeforeEach
	void setUp() throws Exception {
		input = new MockPerson();
		MockitoAnnotations.openMocks(this);
	}
	
	@Test
	void testFindById() {
		Person entity = input.mockEntity(1);
		entity.setId(1L);
		when(repository.findById(1L)).thenReturn(Optional.of(entity));
		
		var result = service.findById(1L);
		assertNotNull(result);
		assertNotNull(result.getKey());
		assertNotNull(result.getLinks());
		System.out.println(result.toString());
		assertNotNull(result.toString().contains("links: [</api/person/1>;rel=\"self\"]"));
		assertEquals("Addres Test1", result.getAddress());
		assertEquals("First Name Test1", result.getFirstName());
		assertEquals("Last Name Test1", result.getLastName());
		assertEquals("Female", result.getGender());
	}

	@Test
	void testFindAll() {
		List<Person> list = input.mockEntityList();
//		
//		when(repository.findAll()).thenReturn(list);
//		
//		var peaple = service.findAll();
//		assertEquals(14, peaple.size());
//		
//		var personOne = peaple.get(1);
//		assertNotNull(personOne);
//		assertNotNull(personOne.getKey());
//		assertNotNull(((RepresentationModel<PersonVO>) personOne).getLinks());
//		System.out.println(personOne.toString());
//		assertNotNull(personOne.toString().contains("links: [</api/person/1>;rel=\"self\"]"));
//		assertEquals("Addres Test1", ((PersonVO) personOne).getAddress());
//		assertEquals("First Name Test1", ((PersonVO) personOne).getFirstName());
//		assertEquals("Last Name Test1", ((PersonVO) personOne).getLastName());
//		assertEquals("Female", ((PersonVO) personOne).getGender());
//		
//		var personSeven = peaple.get(7);
//		assertNotNull(personOne);
//		assertNotNull(personSeven.getKey());
//		assertNotNull(personSeven.getLinks());
//		System.out.println(personSeven.toString());
//		assertNotNull(personSeven.toString().contains("links: [</api/person/7>;rel=\"self\"]"));
//		assertEquals("Addres Test7", personSeven.getAddress());
//		assertEquals("First Name Test7", personSeven.getFirstName());
//		assertEquals("Last Name Test7", personSeven.getLastName());
//		assertEquals("Female", personSeven.getGender());
	}

	

	@Test
	void testCreatePerson() {
		Person entity = input.mockEntity(1);
		Person persited = entity;
		persited.setId(1L);
		
		PersonVO personVO = input.mockVO(1);
		
		when(repository.save(entity)).thenReturn(persited);
		
		var result = service.createPerson(personVO);
		assertNotNull(result);
		assertNotNull(result.getKey());
		assertNotNull(result.getLinks());
		assertNotNull(result.toString().contains("links: [</api/person/1>;rel=\"self\"]"));
		assertEquals("Addres Test1", result.getAddress());
		assertEquals("First Name Test1", result.getFirstName());
		assertEquals("Last Name Test1", result.getLastName());
		assertEquals("Female", result.getGender());
	}
	
	@Test
	void testCreatePersonWithNull() {
		Exception exception = assertThrows( RequiredObjectIsNullException.class, ()-> {
			service.createPerson(null);
		});
		String messageExpect = "It is not allwed save a null object!";
		String messageActual = exception.getMessage();
		assertEquals(messageExpect, messageActual);
	}

	@Test
	void testUpdatePerson() {
		Person entity = input.mockEntity(1);
		entity.setId(1l);
		
		Person persited = entity;
		persited.setId(1L);
		
		PersonVO personVO = input.mockVO(1);
		
		when(repository.findById(1L)).thenReturn(Optional.of(entity));
		when(repository.save(entity)).thenReturn(persited);
		
		var result = service.updatePerson(personVO);
		assertNotNull(result);
		assertNotNull(result.getKey());
		assertNotNull(result.getLinks());
		assertNotNull(result.toString().contains("links: [</api/person/1>;rel=\"self\"]"));
		assertEquals("Addres Test1", result.getAddress());
		assertEquals("First Name Test1", result.getFirstName());
		assertEquals("Last Name Test1", result.getLastName());
		assertEquals("Female", result.getGender());
	}
	
	@Test
	void testUpdatePersonWithNull() {
		Exception exception = assertThrows( RequiredObjectIsNullException.class, ()-> {
			service.updatePerson(null);
		});
		String messageExpect = "It is not allwed save a null object!";
		String messageActual = exception.getMessage();
		assertEquals(messageExpect, messageActual);
	}

	@Test
	void testDeletePerson() {
		Person entity = input.mockEntity(1);
		entity.setId(1L);
		when(repository.findById(1L)).thenReturn(Optional.of(entity));
		
		service.deletePerson(1L);
	}


}

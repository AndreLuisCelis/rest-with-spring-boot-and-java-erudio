package com.celisapp.service;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.celisapp.data.vo.v1.PersonVO;
import com.celisapp.data.vo.v2.PersonVOV2;
import com.celisapp.exceptions.ResourceNotfoundException;
import com.celisapp.mapper.DozerMapper;
import com.celisapp.mapper.custom.PersonMapper;
import com.celisapp.model.Person;
import com.celisapp.repositories.PersonRepository;

@Service
public class PersonService {
	
	@Autowired
	private PersonRepository personRepository;
	private Logger logger = Logger.getLogger(PersonService.class.getName());
	
	@Autowired
	private PersonMapper personMapper;
	
	
	
	public List<PersonVO> findAll(){
		logger.info("Finding all Peaple!");
		return DozerMapper.parseListObjects(personRepository.findAll(), PersonVO.class);
	}
	
	public PersonVO findById( Long id) {
		logger.info("Finding one Person!");
		Person person = personRepository.findById(id)
				.orElseThrow(()-> new ResourceNotfoundException("No records found for this ID"));
		var personVO =  DozerMapper.parseObject(person, PersonVO.class);
		return personVO;
	}
	
	public PersonVO createPerson(PersonVO person) {
		logger.info("creating one Person!");
		var personEntity = DozerMapper.parseObject(person, Person.class);
		Person personCreated = personRepository.save(personEntity);
		return  DozerMapper.parseObject(personCreated, PersonVO.class) ;
	}
	
	public PersonVO updatePerson(PersonVO person) {
		logger.info("editing one Person!");
		Person personForEdit = personRepository.findById(person.getId())
				.orElseThrow(()-> new ResourceNotfoundException("No records found for this ID"));
		personForEdit.setFirstName(person.getFirstName());
		personForEdit.setLastName(person.getLastName());
		personForEdit.setGender(person.getGender());
		personForEdit.setAddress(person.getAddress());
		return DozerMapper.parseObject(personRepository.save(personForEdit), PersonVO.class) ;
	}
	
	public void deletePerson(Long id) {
		logger.info("deleting one Person!");
		var personForDelete = personRepository.findById(id).
		 orElseThrow(()-> new ResourceNotfoundException("No records found for this ID"));
		personRepository.delete(personForDelete);
	}

	public List<PersonVOV2> findAllV2() {
		logger.info("Finding all Peaple!");
		List<PersonVOV2> personsVOV2 = new ArrayList<PersonVOV2>();
		List<Person> personsEntity = personRepository.findAll();
		for (Person person : personsEntity) {
			personsVOV2.add(personMapper.converterToVo(person));
		}
		return personsVOV2;
	}

	public PersonVOV2 createPersonV2(PersonVOV2 person) {
		var personEntity = personMapper.converterToEntity(person);
		return personMapper.converterToVo(personRepository.save(personEntity));
	}
}
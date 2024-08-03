package com.celisapp.service;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.stereotype.Service;

import com.celisapp.controllers.PersonController;
import com.celisapp.data.vo.v1.PersonVO;
import com.celisapp.data.vo.v2.PersonVOV2;
import com.celisapp.exceptions.RequiredObjectIsNullException;
import com.celisapp.exceptions.ResourceNotfoundException;
import com.celisapp.mapper.DozerMapper;
import com.celisapp.mapper.custom.PersonMapper;
import com.celisapp.model.Person;
import com.celisapp.repositories.PersonRepository;

import jakarta.transaction.Transactional;

@Service
public class PersonService {
	
	@Autowired
	private PersonRepository personRepository;
	private Logger logger = Logger.getLogger(PersonService.class.getName());
	
	@Autowired
	private PersonMapper personMapper;
	
	@Autowired
	PagedResourcesAssembler<PersonVO> assembler;
	
	public PagedModel<EntityModel<PersonVO>> findAll(Pageable pageable){
		logger.info("Finding all Peaple!");
		var paeapleEntityPage = personRepository.findAll(pageable);
		var peaploVoPage = paeapleEntityPage.map(person -> DozerMapper.parseObject(person, PersonVO.class));
		peaploVoPage.map(p -> 
			p.add(linkTo(methodOn(PersonController.class).findById(p.getKey())).withSelfRel())
		);
		var link = linkTo(methodOn(PersonController.class).
					findAll(pageable.getPageNumber(),
							pageable.getPageSize(),
							"asc")).withSelfRel();
		return assembler.toModel(peaploVoPage, link);
	}
	
	public PagedModel<EntityModel<PersonVO>> findPersonsByName(String firstName,Pageable pageable){
		logger.info("Finding all Peaple!");
		var paeapleEntityPage = personRepository.findPersonsByName(firstName, pageable);
		var peaploVoPage = paeapleEntityPage.map(person -> DozerMapper.parseObject(person, PersonVO.class));
		peaploVoPage.map(p -> 
			p.add(linkTo(methodOn(PersonController.class).findById(p.getKey())).withSelfRel())
		);
		var link = linkTo(methodOn(PersonController.class).
					findAll(pageable.getPageNumber(),
							pageable.getPageSize(),
							"asc")).withSelfRel();
		return assembler.toModel(peaploVoPage, link);
	}
	
	public PersonVO findById( Long id) {
		logger.info("Finding one Person!");
		Person person = personRepository.findById(id)
				.orElseThrow(()-> new ResourceNotfoundException("No records found for this ID"));
		var personVO =  DozerMapper.parseObject(person, PersonVO.class);
		personVO.add(linkTo(methodOn(PersonController.class).findById(id)).withSelfRel());
		return personVO;
	}
	
	public PersonVO createPerson(PersonVO person) {
		if(person == null) throw new RequiredObjectIsNullException();
		logger.info("creating one Person!");
		var personEntity = DozerMapper.parseObject(person, Person.class);
		Person personCreated = personRepository.save(personEntity);
		PersonVO personVO =  DozerMapper.parseObject(personCreated, PersonVO.class);
		personVO.add(linkTo(methodOn(PersonController.class).findById(personCreated.getId())).withSelfRel());
		return personVO;	
	}
	
	public PersonVO updatePerson(PersonVO person) {
		if(person == null) throw new RequiredObjectIsNullException();
		logger.info("editing one Person!");
		Person personForEdit = personRepository.findById(person.getKey())
				.orElseThrow(()-> new ResourceNotfoundException("No records found for this ID"));
		personForEdit.setFirstName(person.getFirstName());
		personForEdit.setLastName(person.getLastName());
		personForEdit.setGender(person.getGender());
		personForEdit.setAddress(person.getAddress());
		PersonVO personVO =  DozerMapper.parseObject(personRepository.save(personForEdit), PersonVO.class);
		personVO.add(linkTo(methodOn(PersonController.class).findById(person.getKey())).withSelfRel());
		return personVO;
	}
	
	public PersonVO disableOrEnablePersonByPatch(Long id, boolean enabled) {
		
		logger.info("editing one Person verb Patch!");
		Person personForEdit = personRepository.findById(id)
				.orElseThrow(()-> new ResourceNotfoundException("No records found for this ID"));
		personForEdit.setEnable(enabled);
		PersonVO personVO =  DozerMapper.parseObject(personRepository.save(personForEdit), PersonVO.class);
		personVO.add(linkTo(methodOn(PersonController.class).findById(id)).withSelfRel());
		return personVO;
	}
	
	@Transactional
	public PersonVO disablePerson(Long id) {
		
		logger.info("Disabling one person!");
		personRepository.disablePerson(id);
		
		var entity = personRepository.findById(id)
			.orElseThrow(() -> new ResourceNotfoundException("No records found for this ID!"));
		var vo = DozerMapper.parseObject(entity, PersonVO.class);
		vo.add(linkTo(methodOn(PersonController.class).findById(id)).withSelfRel());
		return vo;
	}
	
	@Transactional
	public PersonVO enablePerson(Long id) {
		
		logger.info("Disabling one person!");
		personRepository.enablePerson(id);
		var entity = personRepository.findById(id)
			.orElseThrow(() -> new ResourceNotfoundException("No records found for this ID!"));
		var vo = DozerMapper.parseObject(entity, PersonVO.class);
		vo.add(linkTo(methodOn(PersonController.class).findById(id)).withSelfRel());
		return vo;
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

package com.celisapp.mapper.custom;

import java.util.Date;

import org.springframework.stereotype.Service;

import com.celisapp.data.vo.v2.PersonVOV2;
import com.celisapp.model.Person;

@Service
public class PersonMapper {
	
	public PersonVOV2 converterToVo(Person person) {
		PersonVOV2 personVOV2 = new PersonVOV2();
		personVOV2.setId(person.getId());
		personVOV2.setFirstName(person.getFirstName());
		personVOV2.setLastName(person.getLastName());
		personVOV2.setGender(person.getGender());
		personVOV2.setAddress(person.getAddress());
		personVOV2.setBirthDay(new Date());
		return personVOV2;
	}
	
	public Person converterToEntity(PersonVOV2 personVOV2) {
		Person personEntity = new Person();
		personEntity.setId(personVOV2.getId());
		personEntity.setFirstName(personVOV2.getFirstName());
		personEntity.setLastName(personVOV2.getLastName());
		personEntity.setGender(personVOV2.getGender());
		personEntity.setAddress(personVOV2.getAddress());
		return personEntity;
	}

}

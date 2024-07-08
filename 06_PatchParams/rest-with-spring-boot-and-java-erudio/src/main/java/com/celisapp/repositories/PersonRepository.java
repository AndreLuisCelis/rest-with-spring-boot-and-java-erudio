package com.celisapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.celisapp.model.Person;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {}

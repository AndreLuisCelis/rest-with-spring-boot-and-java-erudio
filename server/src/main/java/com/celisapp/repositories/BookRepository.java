package com.celisapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.celisapp.model.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {}

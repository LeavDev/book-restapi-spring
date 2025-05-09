package com.dwi.restapi.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.dwi.restapi.domain.Book;

@Repository
public interface BookRepository extends CrudRepository<Book, String> {

}

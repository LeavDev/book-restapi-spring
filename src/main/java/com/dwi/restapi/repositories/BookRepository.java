package com.dwi.restapi.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.dwi.restapi.domain.entities.BookEntity;

@Repository
public interface BookRepository extends
                CrudRepository<BookEntity, String>,
                PagingAndSortingRepository<BookEntity, String> {

}

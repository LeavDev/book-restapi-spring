package com.dwi.restapi.services;

import java.util.List;
import java.util.Optional;

import com.dwi.restapi.domain.entities.BookEntity;

public interface BookService {
    BookEntity createBook(String isbn, BookEntity book);

    List<BookEntity> findAll();

    Optional<BookEntity> findOne(String isbn);
}

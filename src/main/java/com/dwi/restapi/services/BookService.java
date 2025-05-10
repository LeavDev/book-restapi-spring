package com.dwi.restapi.services;

import java.util.List;

import com.dwi.restapi.domain.entities.BookEntity;

public interface BookService {
    BookEntity createBook(String isbn, BookEntity book);

    List<BookEntity> findAll();
}

package com.dwi.restapi.services;

import com.dwi.restapi.domain.entities.BookEntity;

public interface BookService {
    BookEntity createBook(String isbn, BookEntity book);
}

package com.dwi.restapi.services.impl;

import org.springframework.stereotype.Service;

import com.dwi.restapi.domain.entities.BookEntity;
import com.dwi.restapi.repositories.BookRepository;
import com.dwi.restapi.services.BookService;

@Service
public class BookServiceImpl implements BookService {

    private BookRepository bookRepository;

    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public BookEntity createBook(String isbn, BookEntity book) {
        book.setIsbn(isbn);
        return bookRepository.save(book);
    }

}

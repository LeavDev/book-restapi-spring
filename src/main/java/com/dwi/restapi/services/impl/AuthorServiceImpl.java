package com.dwi.restapi.services.impl;

import org.springframework.stereotype.Service;

import com.dwi.restapi.domain.entities.AuthorEntity;
import com.dwi.restapi.repositories.AuthorRepository;
import com.dwi.restapi.services.AuthorService;

@Service
public class AuthorServiceImpl implements AuthorService {
    private AuthorRepository authorRepository;

    public AuthorServiceImpl(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Override
    public AuthorEntity createAuthor(AuthorEntity authorEntity) {
        return authorRepository.save(authorEntity);
    }

}

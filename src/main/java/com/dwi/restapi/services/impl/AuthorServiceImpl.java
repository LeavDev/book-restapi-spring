package com.dwi.restapi.services.impl;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

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

    @Override
    public List<AuthorEntity> findAll() {
        return StreamSupport.stream(
                authorRepository.findAll()
                        .spliterator(),
                false)
                .collect(Collectors.toList());
    }

}

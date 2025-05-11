package com.dwi.restapi.services;

import java.util.List;
import java.util.Optional;

import com.dwi.restapi.domain.entities.AuthorEntity;

public interface AuthorService {
    AuthorEntity createAuthor(AuthorEntity authorEntity);

    List<AuthorEntity> findAll();

    Optional<AuthorEntity> findOne(Long id);
}

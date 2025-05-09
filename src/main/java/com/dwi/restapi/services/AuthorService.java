package com.dwi.restapi.services;

import com.dwi.restapi.domain.entities.AuthorEntity;

public interface AuthorService {
    AuthorEntity createAuthor(AuthorEntity authorEntity);
}

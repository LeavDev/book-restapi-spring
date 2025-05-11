package com.dwi.restapi.repositories;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;

import com.dwi.restapi.TestDataUtil;
import com.dwi.restapi.domain.entities.AuthorEntity;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class AuthorRepositoryIntegrationTests {
    private AuthorRepository underTest;

    @Autowired
    public AuthorRepositoryIntegrationTests(AuthorRepository underTest) {
        this.underTest = underTest;
    }

    @Test
    public void testThatAuthorCanBeCreatedAndRecalled() {
        AuthorEntity author = TestDataUtil.createTestAuthorEntityA();
        author.setId(null);
        underTest.save(author);
        Optional<AuthorEntity> result = underTest.findById(author.getId());
        assertThat(result).isPresent();
        assertThat(result.get()).isEqualTo(author);
    }

    @Test
    public void testThatMultipleAuthorCanBeCreatedAndRecalled() {
        AuthorEntity authorA = TestDataUtil.createTestAuthorEntityA();
        authorA.setId(null);
        underTest.save(authorA);
        AuthorEntity authorB = TestDataUtil.createTestAuthorB();
        authorB.setId(null);
        underTest.save(authorB);
        AuthorEntity authorC = TestDataUtil.createTestAuthorC();
        authorC.setId(null);
        underTest.save(authorC);

        Iterable<AuthorEntity> result = underTest.findAll();
        assertThat(result)
                .hasSize(3)
                .containsExactly(authorA, authorB, authorC);
    }

    @Test
    public void testThatGetAuthorsWithAgeLessThan() {
        AuthorEntity testAuthorA = TestDataUtil.createTestAuthorEntityA();
        testAuthorA.setId(null);
        underTest.save(testAuthorA);
        AuthorEntity testAuthorB = TestDataUtil.createTestAuthorB();
        testAuthorB.setId(null);
        underTest.save(testAuthorB);
        AuthorEntity testAuthorC = TestDataUtil.createTestAuthorC();
        testAuthorC.setId(null);
        underTest.save(testAuthorC);

        Iterable<AuthorEntity> result = underTest.ageLessThan(50);
        assertThat(result).containsExactly(testAuthorB, testAuthorC);
    }
}

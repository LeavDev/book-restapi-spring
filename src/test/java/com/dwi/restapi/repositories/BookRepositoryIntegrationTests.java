package com.dwi.restapi.repositories;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.dwi.restapi.TestDataUtil;
import com.dwi.restapi.domain.entities.AuthorEntity;
import com.dwi.restapi.domain.entities.BookEntity;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class BookRepositoryIntegrationTests {
    private BookRepository underTest;

    private AuthorRepository authorRepository;

    @Autowired
    public BookRepositoryIntegrationTests(BookRepository underTest, AuthorRepository authorRepository) {
        this.underTest = underTest;
        this.authorRepository = authorRepository;
    }

    @Test
    public void testThatBookCanBeCreatedAndRecalled() {
        AuthorEntity author = TestDataUtil.createTestAuthorEntityA();
        author.setId(null);
        AuthorEntity savedAuthor = authorRepository.save(author);

        BookEntity book = TestDataUtil.createTestBookEntityA(savedAuthor);
        underTest.save(book);

        Optional<BookEntity> result = underTest.findById(book.getIsbn());
        assertThat(result).isPresent();
        assertThat(result.get()).isEqualTo(book);
    }

    // @Test
    // public void testThatMultipleBooksCanBeCreatedAndRecalled() {
    // Author author = TestDataUtil.createTestAuthorA();
    // authorDao.create(author);

    // Book bookA = TestDataUtil.createTestBookA();
    // bookA.setAuthorId(author.getId());
    // underTest.create(bookA);

    // Book bookB = TestDataUtil.createTestBookB();
    // bookB.setAuthorId(author.getId());
    // underTest.create(bookB);

    // Book bookC = TestDataUtil.createTestBookC();
    // bookC.setAuthorId(author.getId());
    // underTest.create(bookC);

    // List<Book> result = underTest.find();
    // assertThat(result)
    // .hasSize(3)
    // .containsExactly(bookA, bookB, bookC);
    // }

    // @Test
    // public void testThatBookCanBeUpdated() {
    // Author author = TestDataUtil.createTestAuthorA();
    // authorDao.create(author);

    // Book bookA = TestDataUtil.createTestBookA();
    // bookA.setAuthorId(author.getId());
    // underTest.create(bookA);

    // bookA.setTitle("UPDATED");
    // underTest.update(bookA.getIsbn(), bookA);

    // Optional<Book> result = underTest.findOne(bookA.getIsbn());
    // assertThat(result).isPresent();
    // assertThat(result.get()).isEqualTo(bookA);
    // }

    // @Test
    // public void testThatBookCanBeDeleted() {
    // Author author = TestDataUtil.createTestAuthorA();
    // authorDao.create(author);

    // Book bookA = TestDataUtil.createTestBookA();
    // bookA.setAuthorId(author.getId());
    // underTest.create(bookA);

    // underTest.delete(bookA.getIsbn());

    // Optional<Book> result = underTest.findOne(bookA.getIsbn());
    // assertThat(result).isEmpty();
    // }

}

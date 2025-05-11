package com.dwi.restapi.controllers;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.dwi.restapi.TestDataUtil;
import com.dwi.restapi.domain.dto.AuthorDto;
import com.dwi.restapi.domain.entities.AuthorEntity;
import com.dwi.restapi.services.AuthorService;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@AutoConfigureMockMvc
public class AuthorControllerIntegrationTests {

        private MockMvc mockMvc;

        private AuthorService authorService;

        private ObjectMapper objectMapper;

        @Autowired
        public AuthorControllerIntegrationTests(MockMvc mockMvc, AuthorService authorService) {
                this.mockMvc = mockMvc;
                this.authorService = authorService;
                this.objectMapper = new ObjectMapper();
        }

        @Test
        public void testThatCreateAuthorSuccessfullyReturnsHttp201Created() throws Exception {
                AuthorEntity testAuthorA = TestDataUtil.createTestAuthorEntityA();
                testAuthorA.setId(null);
                String authorJson = objectMapper.writeValueAsString(testAuthorA);

                mockMvc.perform(
                                MockMvcRequestBuilders.post("/authors")
                                                .contentType(MediaType.APPLICATION_JSON)
                                                .content(authorJson))
                                .andExpect(
                                                MockMvcResultMatchers.status().isCreated());
        }

        @Test
        public void testThatCreateAuthorSuccessfullyReturnsSavedAuthor() throws Exception {
                AuthorEntity testAuthorA = TestDataUtil.createTestAuthorEntityA();
                testAuthorA.setId(null);
                String authorJson = objectMapper.writeValueAsString(testAuthorA);

                mockMvc.perform(
                                MockMvcRequestBuilders.post("/authors")
                                                .contentType(MediaType.APPLICATION_JSON)
                                                .content(authorJson))
                                .andExpect(
                                                MockMvcResultMatchers.jsonPath("$.id").isNumber())
                                .andExpect(
                                                MockMvcResultMatchers.jsonPath("$.name").value("Abigail Rose"))
                                .andExpect(
                                                MockMvcResultMatchers.jsonPath("$.age").value(80));
        }

        @Test
        public void testThatListAuthorsReturnsHttpStatus200() throws Exception {
                mockMvc.perform(MockMvcRequestBuilders.get("/authors")
                                .contentType(MediaType.APPLICATION_JSON))
                                .andExpect(MockMvcResultMatchers.status().isOk());
        }

        @Test
        public void testThatListAuthorsReturnsListOfAuthors() throws Exception {
                AuthorEntity testAuthorA = TestDataUtil.createTestAuthorEntityA();
                testAuthorA.setId(null);
                authorService.save(testAuthorA);

                mockMvc.perform(MockMvcRequestBuilders.get("/authors")
                                .contentType(MediaType.APPLICATION_JSON)).andExpect(
                                                MockMvcResultMatchers.jsonPath("$[0].id").isNumber())
                                .andExpect(
                                                MockMvcResultMatchers.jsonPath("$[0].name").value("Abigail Rose"))
                                .andExpect(
                                                MockMvcResultMatchers.jsonPath("$[0].age").value(80));
        }

        @Test
        public void testThatGetAuthorReturnsHttpStatus200WhenAuthorExist() throws Exception {
                AuthorEntity testAuthorA = TestDataUtil.createTestAuthorEntityA();
                testAuthorA.setId(null);
                authorService.save(testAuthorA);

                mockMvc.perform(MockMvcRequestBuilders.get("/authors/1")
                                .contentType(MediaType.APPLICATION_JSON))
                                .andExpect(MockMvcResultMatchers.status().isOk());
        }

        @Test
        public void testThatGetAuthorReturnsAuthorWhenAuthorExist() throws Exception {
                AuthorEntity testAuthorA = TestDataUtil.createTestAuthorEntityA();
                testAuthorA.setId(null);
                authorService.save(testAuthorA);

                mockMvc.perform(MockMvcRequestBuilders.get("/authors/1")
                                .contentType(MediaType.APPLICATION_JSON))
                                .andExpect(
                                                MockMvcResultMatchers.jsonPath("$.id").value(1))
                                .andExpect(
                                                MockMvcResultMatchers.jsonPath("$.name").value("Abigail Rose"))
                                .andExpect(
                                                MockMvcResultMatchers.jsonPath("$.age").value(80));
        }

        @Test
        public void testThatGetAuthorReturnsHttpStatus404WhenNoAuthorExist() throws Exception {
                AuthorEntity testAuthorA = TestDataUtil.createTestAuthorEntityA();
                testAuthorA.setId(null);
                authorService.save(testAuthorA);

                mockMvc.perform(MockMvcRequestBuilders.get("/authors/99")
                                .contentType(MediaType.APPLICATION_JSON))
                                .andExpect(MockMvcResultMatchers.status().isNotFound());
        }

        @Test
        public void testThatFullUpdateAuthorReturnsHttpStatus404WhenNoAuthorExist() throws Exception {
                AuthorDto testAuthorDtoA = TestDataUtil.createTestAuthorDtoA();
                String authorDtoJson = objectMapper.writeValueAsString(testAuthorDtoA);

                mockMvc.perform(MockMvcRequestBuilders.put("/authors/99")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(authorDtoJson))
                                .andExpect(MockMvcResultMatchers.status().isNotFound());
        }

        @Test
        public void testThatFullUpdateAuthorReturnsHttpStatus200WhenAuthorExist() throws Exception {
                AuthorEntity testAuthorEntityA = TestDataUtil.createTestAuthorEntityA();
                testAuthorEntityA.setId(null);
                AuthorEntity savedAuthor = authorService.save(testAuthorEntityA);

                AuthorDto testAuthorDtoA = TestDataUtil.createTestAuthorDtoA();
                String authorDtoJson = objectMapper.writeValueAsString(testAuthorDtoA);

                mockMvc.perform(MockMvcRequestBuilders.put("/authors/" + savedAuthor.getId())
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(authorDtoJson))
                                .andExpect(MockMvcResultMatchers.status().isOk());
        }

        @Test
        public void testThatFullUpdateUpdatesExistingAuthor() throws Exception {
                AuthorEntity testAuthorEntityA = TestDataUtil.createTestAuthorEntityA();
                testAuthorEntityA.setId(null);
                AuthorEntity savedAuthor = authorService.save(testAuthorEntityA);

                AuthorDto authorDto = TestDataUtil.createTestAuthorDtoB();
                authorDto.setId(savedAuthor.getId());

                String authorDtoUpdateJson = objectMapper.writeValueAsString(authorDto);

                mockMvc.perform(MockMvcRequestBuilders.put("/authors/" + savedAuthor.getId())
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(authorDtoUpdateJson))
                                .andExpect(
                                                MockMvcResultMatchers.jsonPath("$.id").value(savedAuthor.getId()))
                                .andExpect(
                                                MockMvcResultMatchers.jsonPath("$.name").value(authorDto.getName()))
                                .andExpect(
                                                MockMvcResultMatchers.jsonPath("$.age").value(authorDto.getAge()));
        }
}

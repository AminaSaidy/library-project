package uz.aminasaidakhmedova.library_project.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import uz.aminasaidakhmedova.library_project.dto.AuthorCreateDto;
import uz.aminasaidakhmedova.library_project.dto.AuthorDto;
import uz.aminasaidakhmedova.library_project.dto.AuthorUpdateDto;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
public class AuthorRestControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testGetAuthorById() throws Exception {
        Long id = 1L;
        AuthorDto authorDto = AuthorDto.builder()
                .id(id)
                .name("Александр")
                .surname("Пушкин")
                .build();

        mockMvc.perform(MockMvcRequestBuilders.get("/author/{id}", id))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(authorDto.getId()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value(authorDto.getName()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.surname").value(authorDto.getSurname()));
    }

    @Test
    public void testGetAuthorByName() throws Exception {
        String name = "Александр";
        AuthorDto authorDto = AuthorDto.builder()
                .id(1L)
                .name(name)
                .surname("Пушкин")
                .build();

        mockMvc.perform(MockMvcRequestBuilders.get("/author").param("name", name))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value(authorDto.getName()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.surname").value(authorDto.getSurname()));
    }

    @Test
    public void testCreateAuthor() throws Exception {
        AuthorCreateDto authorCreateDto = new AuthorCreateDto();
        authorCreateDto.setName("Александр");
        authorCreateDto.setSurname("Пушкин");

        mockMvc.perform(MockMvcRequestBuilders.post("/author/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(authorCreateDto)))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value(authorCreateDto.getName()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.surname").value(authorCreateDto.getSurname()));
    }

    @Test
    public void testUpdateAuthor() throws Exception {
        AuthorUpdateDto authorUpdateDto = new AuthorUpdateDto();
        authorUpdateDto.setId(1L);
        authorUpdateDto.setName("\u0410\u043B\u0435\u043A\u0441\u0435\u0439");
        authorUpdateDto.setSurname("\u0422\u043E\u043B\u0441\u0442\u043E\u0439");

        mockMvc.perform(MockMvcRequestBuilders.put("/author/update")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(authorUpdateDto)))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(authorUpdateDto.getId()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value(authorUpdateDto.getName()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.surname").value(authorUpdateDto.getSurname()));
    }

//    @Test
//    public void testDeleteAuthor() throws Exception {
//        Long id = 1L;
//
//        mockMvc.perform(MockMvcRequestBuilders.delete("/author/delete/{id}", id))
//                .andExpect(status().isOk());
//    }
}

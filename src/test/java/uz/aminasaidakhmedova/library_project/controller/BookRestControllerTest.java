package uz.aminasaidakhmedova.library_project.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import uz.aminasaidakhmedova.library_project.dto.BookCreateDto;
import uz.aminasaidakhmedova.library_project.dto.BookDto;

import java.util.HashSet;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
public class BookRestControllerTest {

    @Autowired
    private MockMvc mockMvc;

//    @Test
//    public void testGetBookById() throws Exception {
//        Long id = 1L;
//        BookDto bookDto = BookDto.builder()
//                .id(id)
//                .name("Book Name")
//                .genre("Fiction")
//                .build();
//
//        mockMvc.perform(MockMvcRequestBuilders.get("/book/{id}", id))
//                .andExpect(status().isOk())
//                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(bookDto.getId()))
//                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value(bookDto.getName()))
//                .andExpect(MockMvcResultMatchers.jsonPath("$.genre").value(bookDto.getGenre()));
//    }

//    @Test
//    public void testCreateBook() throws Exception {
//        BookCreateDto bookCreateDto = new BookCreateDto("Book Name", 1L, new HashSet<>());
//        BookDto bookDto = BookDto.builder()
//                .id(1L)
//                .name(bookCreateDto.getName())
//                .genre("Fiction")
//                .build();
//
//        mockMvc.perform(MockMvcRequestBuilders.post("/book")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(new ObjectMapper().writeValueAsString(bookCreateDto)))
//                .andExpect(status().isCreated())
//                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(bookDto.getId()))
//                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value(bookDto.getName()))
//                .andExpect(MockMvcResultMatchers.jsonPath("$.genre").value(bookDto.getGenre()));
//    }

//    @Test
//    public void testDeleteBook() throws Exception {
//        Long id = 1L;
//        mockMvc.perform(MockMvcRequestBuilders.delete("/book/{id}", id))
//                .andExpect(status().isNoContent());
//    }
}

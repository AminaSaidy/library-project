package uz.aminasaidakhmedova.library_project.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import uz.aminasaidakhmedova.library_project.dto.AuthorCreateDto;
import uz.aminasaidakhmedova.library_project.dto.AuthorDto;
import uz.aminasaidakhmedova.library_project.dto.BookCreateDto;
import uz.aminasaidakhmedova.library_project.dto.BookDto;
import uz.aminasaidakhmedova.library_project.service.BookService;

import java.util.HashSet;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
public class BookRestControllerTest {

    @Autowired
    private MockMvc mockMvc;


    @MockBean
    private BookService bookService;

    @Test
    public void testGetBookByName() throws Exception {
        String name = "Book Name";
        BookDto bookDto = BookDto.builder()
                .id(1L)
                .name(name)
                .genre("Fiction")
                .build();

        when(bookService.getByNameV1(name)).thenReturn(bookDto);

        mockMvc.perform(MockMvcRequestBuilders.get("/book").param("name", name))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value(bookDto.getName()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.genre").value(bookDto.getGenre()));
    }

//    @Test
//    public void testCreateBook() throws Exception {
//        BookCreateDto bookCreateDto = new BookCreateDto();
//        bookCreateDto.setName("Book name");
//        bookCreateDto.setGenreId(1L);
//
//        mockMvc.perform(MockMvcRequestBuilders.post("/book/create")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(new ObjectMapper().writeValueAsString(bookCreateDto)))
//                .andExpect(status().isOk())
//                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value(bookCreateDto.getName()))
//                .andExpect(MockMvcResultMatchers.jsonPath("$.genreId").value(bookCreateDto.getGenreId()));
//    }

    @Test
    public void testDeleteBook() throws Exception {
        Long id = 1L;
        mockMvc.perform(MockMvcRequestBuilders.delete("/book/delete/{id}", id))
                .andExpect(status().isOk());
    }
}

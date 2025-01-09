package uz.aminasaidakhmedova.library_project.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import uz.aminasaidakhmedova.library_project.dto.AuthorCreateDto;
import uz.aminasaidakhmedova.library_project.dto.AuthorDto;
import uz.aminasaidakhmedova.library_project.dto.AuthorUpdateDto;
import uz.aminasaidakhmedova.library_project.model.Author;
import uz.aminasaidakhmedova.library_project.model.Book;
import uz.aminasaidakhmedova.library_project.repository.AuthorRepository;

import java.util.*;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
public class AuthorServiceTest {

    @Mock
    AuthorRepository authorRepository;

    @InjectMocks
    AuthorServiceImpl authorService;

    @Test
    public void testGetAuthorById() {
        Long id = 1L;
        String name = "John";
        String surname = "Doe";
        Set<Book> books = new HashSet<>();

        Author author = new Author(id, name, surname, books);

        when(authorRepository.findById(id)).thenReturn(Optional.of(author));

        AuthorDto authorDto = authorService.getAuthorById(id);
        verify(authorRepository).findById(id);

        Assertions.assertEquals(authorDto.getId(), author.getId());
        Assertions.assertEquals(authorDto.getName(), author.getName());
        Assertions.assertEquals(authorDto.getSurname(), author.getSurname());
    }

    @Test
    public void testGetAuthorByIdFailed() {
        Long id = 1L;

        when(authorRepository.findById(id)).thenReturn(Optional.empty());

        Assertions.assertThrows(NoSuchElementException.class, () -> authorService.getAuthorById(id));
        verify(authorRepository).findById(id);
    }

    @Test
    public void testGetAuthorByName() {
        String name = "John";
        Author author = new Author(1L, name, "Doe", new HashSet<>());

        when(authorRepository.findAuthorByName(name)).thenReturn(Optional.of(author));

        AuthorDto authorDto = authorService.getAuthorByName(name);
        verify(authorRepository).findAuthorByName(name);

        Assertions.assertEquals(authorDto.getName(), author.getName());
    }

    @Test
    public void testCreateAuthor() {
        AuthorCreateDto authorCreateDto = new AuthorCreateDto("John", "Doe");
        Author author = new Author(1L, "John", "Doe", new HashSet<>());

        when(authorRepository.save(any(Author.class))).thenReturn(author);

        AuthorDto authorDto = authorService.createAuthor(authorCreateDto);
        verify(authorRepository).save(any(Author.class));

        Assertions.assertEquals(authorDto.getName(), author.getName());
    }

    @Test
    public void testUpdateAuthor() {
        Long id = 1L;
        AuthorUpdateDto authorUpdateDto = new AuthorUpdateDto();
        authorUpdateDto.setId(id);
        authorUpdateDto.setName("Updated Name");
        authorUpdateDto.setSurname("Updated Surname");

        Author existingAuthor = new Author(id, "Old Name", "Old Surname", new HashSet<>());
        Author updatedAuthor = new Author(id, "Updated Name", "Updated Surname", new HashSet<>());

        when(authorRepository.findById(id)).thenReturn(Optional.of(existingAuthor));
        when(authorRepository.save(any(Author.class))).thenReturn(updatedAuthor);

        AuthorDto result = authorService.updateAuthor(authorUpdateDto);

        verify(authorRepository).findById(id);
        verify(authorRepository).save(any(Author.class));

        Assertions.assertEquals(result.getId(), updatedAuthor.getId());
        Assertions.assertEquals(result.getName(), updatedAuthor.getName());
        Assertions.assertEquals(result.getSurname(), updatedAuthor.getSurname());
    }

    @Test
    public void testUpdateAuthorFailed() {
        Long id = 1L;
        AuthorUpdateDto authorUpdateDto = new AuthorUpdateDto();
        authorUpdateDto.setId(id);
        authorUpdateDto.setName("Updated Name");
        authorUpdateDto.setSurname("Updated Surname");

        when(authorRepository.findById(id)).thenReturn(Optional.empty());

        Assertions.assertThrows(NoSuchElementException.class, () -> authorService.updateAuthor(authorUpdateDto));
        verify(authorRepository).findById(id);
    }

    @Test
    public void testDeleteAuthor() {
        Long id = 1L;

        when(authorRepository.existsById(id)).thenReturn(true);

        authorService.deleteAuthor(id);

        verify(authorRepository).existsById(id);
        verify(authorRepository).deleteById(id);
    }

    @Test
    public void testDeleteAuthorFailed() {
        Long id = 1L;

        when(authorRepository.existsById(id)).thenReturn(false);

        Assertions.assertThrows(NoSuchElementException.class, () -> authorService.deleteAuthor(id));
        verify(authorRepository).existsById(id);
    }

    @Test
    public void testGetAllAuthors() {
        Author author1 = new Author(1L, "John", "Doe", new HashSet<>());
        Author author2 = new Author(2L, "Jane", "Smith", new HashSet<>());
        List<Author> authors = List.of(author1, author2);

        when(authorRepository.findAll()).thenReturn(authors);

        List<AuthorDto> result = authorService.getAllAuthors();

        verify(authorRepository).findAll();

        Assertions.assertEquals(result.size(), authors.size());
        Assertions.assertEquals(result.get(0).getId(), author1.getId());
        Assertions.assertEquals(result.get(1).getId(), author2.getId());
    }

}

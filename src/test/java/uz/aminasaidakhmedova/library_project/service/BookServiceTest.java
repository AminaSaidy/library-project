package uz.aminasaidakhmedova.library_project.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.domain.Specification;
import uz.aminasaidakhmedova.library_project.dto.BookCreateDto;
import uz.aminasaidakhmedova.library_project.dto.BookDto;
import uz.aminasaidakhmedova.library_project.dto.BookUpdateDto;
import uz.aminasaidakhmedova.library_project.model.Author;
import uz.aminasaidakhmedova.library_project.model.Book;
import uz.aminasaidakhmedova.library_project.model.Genre;
import uz.aminasaidakhmedova.library_project.repository.BookRepository;

import java.util.*;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
public class BookServiceTest {
    @Mock
    BookRepository bookRepository;

    @InjectMocks
    BookServiceImpl bookService;

    @Test
    public void testGetByNameV1() {
        String name = "BookName";
        Book book = new Book(1L, name, new Genre(1L, "GenreName", new HashSet<>()), new HashSet<>());

        when(bookRepository.findBookByName(name)).thenReturn(Optional.of(book));

        BookDto bookDto = bookService.getByNameV1(name);

        verify(bookRepository).findBookByName(name);
        Assertions.assertEquals(bookDto.getName(), book.getName());
        Assertions.assertEquals(bookDto.getGenre(), book.getGenre().getName());
    }

    @Test
    public void testGetByNameV1Failed() {
        String name = "NonExistentBook";

        when(bookRepository.findBookByName(name)).thenReturn(Optional.empty());

        Assertions.assertThrows(NoSuchElementException.class, () -> bookService.getByNameV1(name));
        verify(bookRepository).findBookByName(name);
    }

    @Test
    public void testGetByNameV2() {
        String name = "BookName";
        Book book = new Book(1L, name, new Genre(1L, "GenreName", new HashSet<>()), new HashSet<>());

        when(bookRepository.findBookByNameBySql(name)).thenReturn(Optional.of(book));

        BookDto bookDto = bookService.getByNameV2(name);

        verify(bookRepository).findBookByNameBySql(name);
        Assertions.assertEquals(bookDto.getName(), book.getName());
        Assertions.assertEquals(bookDto.getGenre(), book.getGenre().getName());
    }

    @Test
    public void testGetByNameV2Failed() {
        String name = "NonExistentBook";

        when(bookRepository.findBookByNameBySql(name)).thenReturn(Optional.empty());

        Assertions.assertThrows(NoSuchElementException.class, () -> bookService.getByNameV2(name));
        verify(bookRepository).findBookByNameBySql(name);
    }

    @Test
    public void testGetByNameV3() {
        String name = "BookName";
        Book book = new Book(1L, name, new Genre(1L, "GenreName", new HashSet<>()), new HashSet<>());

        Specification<Book> specification = Specification.where((root, query, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("name"), name));

        when(bookRepository.findOne(specification)).thenReturn(Optional.of(book));

        BookDto bookDto = bookService.getByNameV3(name);

        verify(bookRepository).findOne(specification);
        Assertions.assertEquals(bookDto.getName(), book.getName());
        Assertions.assertEquals(bookDto.getGenre(), book.getGenre().getName());
    }

    @Test
    public void testGetByNameV3Failed() {
        String name = "NonExistentBook";

        Specification<Book> specification = Specification.where((root, query, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("name"), name));

        when(bookRepository.findOne(specification)).thenReturn(Optional.empty());

        Assertions.assertThrows(NoSuchElementException.class, () -> bookService.getByNameV3(name));
        verify(bookRepository).findOne(specification);
    }

    @Test
    public void testUpdateBook() {
        Long id = 1L;
        BookUpdateDto bookUpdateDto = new BookUpdateDto();
        bookUpdateDto.setId(id);
        bookUpdateDto.setName("UpdatedBookName");

        Book existingBook = new Book(id, "OldBookName", new Genre(1L, "GenreName", new HashSet<>()), new HashSet<>());
        Book updatedBook = new Book(id, "UpdatedBookName", new Genre(1L, "GenreName", new HashSet<>()), new HashSet<>());

        when(bookRepository.findById(id)).thenReturn(Optional.of(existingBook));
        when(bookRepository.save(any(Book.class))).thenReturn(updatedBook);

        BookDto bookDto = bookService.updateBook(bookUpdateDto);

        verify(bookRepository).findById(id);
        verify(bookRepository).save(any(Book.class));

        Assertions.assertEquals(bookDto.getName(), updatedBook.getName());
    }

    @Test
    public void testDeleteBook() {
        Long id = 1L;

        when(bookRepository.existsById(id)).thenReturn(true);

        bookService.deleteBook(id);

        verify(bookRepository).existsById(id);
        verify(bookRepository).deleteById(id);
    }

    @Test
    public void testDeleteBookFailed() {
        Long id = 1L;

        when(bookRepository.existsById(id)).thenReturn(false);

        Assertions.assertThrows(NoSuchElementException.class, () -> bookService.deleteBook(id));
        verify(bookRepository).existsById(id);
    }

    @Test
    public void testGetAllBooks() {
        Book book1 = new Book(1L, "Book1", new Genre(1L, "GenreName1", new HashSet<>()), new HashSet<>());
        Book book2 = new Book(2L, "Book2", new Genre(1L, "GenreName2", new HashSet<>()), new HashSet<>());
        List<Book> books = List.of(book1, book2);

        when(bookRepository.findAll()).thenReturn(books);

        List<BookDto> result = bookService.getAllBooks();

        verify(bookRepository).findAll();

        Assertions.assertEquals(result.size(), books.size());
        Assertions.assertEquals(result.get(0).getName(), book1.getName());
        Assertions.assertEquals(result.get(1).getName(), book2.getName());
    }

}


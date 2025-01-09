package uz.aminasaidakhmedova.library_project.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import uz.aminasaidakhmedova.library_project.dto.BookCreateDto;
import uz.aminasaidakhmedova.library_project.dto.BookDto;
import uz.aminasaidakhmedova.library_project.dto.BookUpdateDto;
import uz.aminasaidakhmedova.library_project.model.Book;
import uz.aminasaidakhmedova.library_project.model.Genre;
import uz.aminasaidakhmedova.library_project.repository.BookRepository;

import java.util.HashSet;
import java.util.Optional;

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

    }

//    @Test
//    public void testCreateBook() {
//        BookCreateDto bookCreateDto = new BookCreateDto("Book Name", 1L, new List<Long>());
//        Genre genre = new Genre(1L, "Fiction", new HashSet<>());
//        Book book = new Book(1L, "Book Name", genre, new HashSet<>());
//
//        when(bookRepository.save(any(Book.class))).thenReturn(book);
//
//        BookDto bookDto = bookService.createBook(bookCreateDto);
//        verify(bookRepository).save(any(Book.class));
//
//        Assertions.assertEquals(bookDto.getName(), book.getName());
//    }

//    @Test
//    public void testUpdateBook() {
//        Long bookId = 1L;
//        BookUpdateDto bookUpdateDto = new BookUpdateDto("Updated Name", 2L, new HashSet<>());
//        Genre updatedGenre = new Genre(2L, "Non-Fiction");
//        Book existingBook = new Book(bookId, "Old Name", new Genre(1L, "Fiction"), new HashSet<>());
//        Book updatedBook = new Book(bookId, bookUpdateDto.getName(), updatedGenre, new HashSet<>());
//
//        when(bookRepository.findById(bookId)).thenReturn(Optional.of(existingBook));
//        when(bookRepository.save(any(Book.class))).thenReturn(updatedBook);
//
//        BookDto bookDto = bookService.updateBook(bookId, bookUpdateDto);
//        verify(bookRepository).findById(bookId);
//        verify(bookRepository).save(any(Book.class));
//
//        Assertions.assertEquals(bookDto.getName(), updatedBook.getName());
//        Assertions.assertEquals(bookDto.getGenre(), updatedBook.getGenre().getName());
//    }

//    @Test
//    public void testDeleteBook() {
//        Long bookId = 1L;
//        doNothing().when(bookRepository).deleteById(bookId);
//
//        bookService.deleteBook(bookId);
//        verify(bookRepository).deleteById(bookId);
//    }
}

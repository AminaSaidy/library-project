package uz.aminasaidakhmedova.library_project.service;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import uz.aminasaidakhmedova.library_project.dto.AuthorDto;
import uz.aminasaidakhmedova.library_project.dto.BookCreateDto;
import uz.aminasaidakhmedova.library_project.dto.BookDto;
import uz.aminasaidakhmedova.library_project.dto.BookUpdateDto;
import uz.aminasaidakhmedova.library_project.model.Author;
import uz.aminasaidakhmedova.library_project.model.Book;
import uz.aminasaidakhmedova.library_project.model.Genre;
import uz.aminasaidakhmedova.library_project.repository.AuthorRepository;
import uz.aminasaidakhmedova.library_project.repository.BookRepository;
import uz.aminasaidakhmedova.library_project.repository.GenreRepository;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;
    private final GenreRepository genreRepository;
    private final AuthorRepository authorRepository;

    @Override
    public BookDto getByNameV1(String name) {
        log.info("Try to find book by name {}", name);
        Book book = bookRepository.findBookByName(name).orElseThrow(() -> {
            log.error("Book with name {} not found", name);
            return new NoSuchElementException("Book not found");
        });
        BookDto bookDto = convertEntityToDto(book);
        log.info("Found book: {}", bookDto);
        return bookDto;
    }

    @Override
    public BookDto getByNameV2(String name) {
        log.info("Try to find book by name (SQL) {}", name);
        Book book = bookRepository.findBookByNameBySql(name).orElseThrow(() -> {
            log.error("Book with name {} not found", name);
            return new NoSuchElementException("Book not found");
        });
        BookDto bookDto = convertEntityToDto(book);
        log.info("Found book: {}", bookDto);
        return bookDto;
    }

    @Override
    public BookDto getByNameV3(String name) {
        log.info("Try to find book by name (Specification) {}", name);
        Specification<Book> specification = Specification.where((root, query, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("name"), name));
        Book book = bookRepository.findOne(specification).orElseThrow(() -> {
            log.error("Book with name {} not found", name);
            return new NoSuchElementException("Book not found");
        });
        BookDto bookDto = convertEntityToDto(book);
        log.info("Found book: {}", bookDto);
        return bookDto;
    }

    @Override
    public BookDto createBook(BookCreateDto bookCreateDto) {
        log.info("Creating book with data: {}", bookCreateDto);
        Book book = bookRepository.save(convertDtoToEntity(bookCreateDto));
        BookDto bookDto = convertEntityToDto(book);
        log.info("Created book: {}", bookDto);
        return bookDto;
    }

    @Override
    public BookDto updateBook(BookUpdateDto bookUpdateDto) {
        log.info("Updating book with id {}", bookUpdateDto.getId());
        Book book = bookRepository.findById(bookUpdateDto.getId()).orElseThrow(() -> {
            log.error("Book with id {} not found", bookUpdateDto.getId());
            return new NoSuchElementException("Book not found");
        });
        book.setName(bookUpdateDto.getName());
        Book savedBook = bookRepository.save(book);
        BookDto bookDto = convertEntityToDto(savedBook);
        log.info("Updated book: {}", bookDto);
        return bookDto;
    }

    @Override
    public void deleteBook(Long id) {
        log.info("Deleting book with id {}", id);
        if (!bookRepository.existsById(id)) {
            log.error("Book with id {} not found", id);
            throw new NoSuchElementException("Book not found");
        }
        bookRepository.deleteById(id);
        log.info("Deleted book with id {}", id);
    }

    @Override
    public List<BookDto> getAllBooks() {
        log.info("Fetching all books");
        List<Book> books = bookRepository.findAll();
        List<BookDto> bookDtos = books.stream().map(this::convertEntityToDto).collect(Collectors.toList());
        log.info("Fetched {} books", bookDtos.size());
        return bookDtos;
    }

    private Book convertDtoToEntity(BookCreateDto bookCreateDto){
        Genre genre = genreRepository.findById(bookCreateDto.getGenreId())
                .orElseThrow(() -> new RuntimeException("Genre not found"));

        Set<Author> authors = Optional.ofNullable(bookCreateDto.getAuthorIds())
                .orElse(Collections.emptyList()) // If authorIds is null, use an empty list
                .stream()
                .map(id -> authorRepository.findById(id)
                        .orElseThrow(() -> new RuntimeException("Author not found")))
                .collect(Collectors.toSet());

        return Book.builder()
                .name(bookCreateDto.getName())
                .genre(genre)
                .authors(authors)
                .build();
    }

    private BookDto convertEntityToDto(Book book) {
        List<AuthorDto> authorDtoList = book.getAuthors().stream()
                .map(author -> AuthorDto.builder()
                        .name(author.getName())
                        .surname(author.getSurname())
                        .build())
                .collect(Collectors.toList());

        return BookDto.builder()
                .id(book.getId())
                .genre(book.getGenre().getName())
                .name(book.getName())
                .authors(authorDtoList)
                .build();
    }
}

package uz.aminasaidakhmedova.library_project.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import uz.aminasaidakhmedova.library_project.dto.AuthorDto;
import uz.aminasaidakhmedova.library_project.dto.BookDto;
import uz.aminasaidakhmedova.library_project.dto.GenreDto;
import uz.aminasaidakhmedova.library_project.model.Genre;
import uz.aminasaidakhmedova.library_project.repository.GenreRepository;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class GenreServiceImpl implements GenreService {

    private final GenreRepository genreRepository;

    @Override
    public GenreDto getGenreById(Long id) {
        log.info("Try to find genre by id {}", id);
        Genre genre = genreRepository.findById(id).orElseThrow(() -> {
                log.error("Genre with id {} not found", id);
        return new NoSuchElementException("Genre not found");
    });
        GenreDto genreDto = convertEntityToDto(genre);
        log.info("Found genre: {}", genreDto);
        return genreDto;
    }

    private GenreDto convertEntityToDto(Genre genre) {

        List<BookDto> bookDtoList = genre.getBooks().stream()
                .map(book -> BookDto.builder()
                        .authors(book.getAuthors().stream()
                                .map(author -> AuthorDto.builder()
                                        .name(author.getName())
                                        .surname(author.getSurname())
                                        .build())
                                .collect(Collectors.toList()))
                        .build())
                .toList();

        GenreDto genreDto = GenreDto.builder()
                .id(genre.getId())
                .name(genre.getName())
                .books(bookDtoList)
                .build();
        return genreDto;
    }
}

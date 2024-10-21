package uz.aminasaidakhmedova.library_project.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.aminasaidakhmedova.library_project.dto.AuthorDto;
import uz.aminasaidakhmedova.library_project.dto.BookDto;
import uz.aminasaidakhmedova.library_project.dto.GenreDto;
import uz.aminasaidakhmedova.library_project.model.Genre;
import uz.aminasaidakhmedova.library_project.repository.GenreRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class GenreServiceImpl implements GenreService {

    private final GenreRepository genreRepository;

    @Override
    public GenreDto getGenreById(Long id) {
        Genre genre = genreRepository.findById(id).orElseThrow();
        GenreDto genreDto = convertEntityToDto(genre);
        return genreDto;
    }

    private GenreDto convertEntityToDto(Genre genre) {

        List<BookDto> bookDtoList = genre.getBooks().stream()
                .map(book -> BookDto.builder()
                        .genre(book.getGenre().getName())
                        .name(book.getName())
                        .id(book.getId())
                        .authors(book.getAuthors().stream()
                                .map(author -> AuthorDto.builder()
                                        .id(author.getId()) // Assuming AuthorDto has id
                                        .name(author.getName()) // Assuming AuthorDto has name
                                        .surname(author.getSurname()) // Assuming AuthorDto has surname
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

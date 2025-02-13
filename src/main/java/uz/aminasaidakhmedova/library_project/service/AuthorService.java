package uz.aminasaidakhmedova.library_project.service;

import uz.aminasaidakhmedova.library_project.dto.AuthorCreateDto;
import uz.aminasaidakhmedova.library_project.dto.AuthorDto;
import uz.aminasaidakhmedova.library_project.dto.AuthorUpdateDto;
import uz.aminasaidakhmedova.library_project.model.Author;

import java.util.List;

public interface AuthorService {
    AuthorDto getAuthorById(Long id);
    AuthorDto getAuthorByName(String name);
    AuthorDto getAuthorByNameBySql(String name);
    AuthorDto getAuthorByNameV3(String name);
    AuthorDto createAuthor(AuthorCreateDto authorCreateDto);
    AuthorDto updateAuthor(AuthorUpdateDto authorUpdateDto);
    void deleteAuthor(Long id);
    List<AuthorDto> getAllAuthors();
}

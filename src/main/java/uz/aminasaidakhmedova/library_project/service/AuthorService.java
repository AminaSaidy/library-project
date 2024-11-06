package uz.aminasaidakhmedova.library_project.service;

import uz.aminasaidakhmedova.library_project.dto.AuthorDto;

public interface AuthorService {
    AuthorDto getAuthorById(Long id);
    AuthorDto getAuthorByName(String name);
    AuthorDto getAuthorByNameBySql(String name);
    AuthorDto getAuthorByNameV3(String name);
}

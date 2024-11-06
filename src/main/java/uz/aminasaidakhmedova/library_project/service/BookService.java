package uz.aminasaidakhmedova.library_project.service;

import uz.aminasaidakhmedova.library_project.dto.BookDto;

public interface BookService {
    BookDto getByNameV1(String name);
    BookDto getByNameV2(String name);
    BookDto getByNameV3(String name);
}

package uz.aminasaidakhmedova.library_project.controller.rest;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import uz.aminasaidakhmedova.library_project.dto.GenreDto;
import uz.aminasaidakhmedova.library_project.service.GenreService;

@RestController
@RequiredArgsConstructor
@SecurityRequirement(name = "library-users")
public class GenreRestController {
    private final GenreService genreService;

    @GetMapping("/genre/{id}")
    GenreDto getGenreDtoById(@PathVariable("id") long id) {
        return genreService.getGenreById(id);
    }
}

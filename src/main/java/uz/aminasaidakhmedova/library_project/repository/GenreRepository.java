package uz.aminasaidakhmedova.library_project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.aminasaidakhmedova.library_project.model.Genre;

public interface GenreRepository extends JpaRepository<Genre, Long> {
}

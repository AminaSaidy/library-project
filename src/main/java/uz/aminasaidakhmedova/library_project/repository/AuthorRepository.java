package uz.aminasaidakhmedova.library_project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.aminasaidakhmedova.library_project.model.Author;

public interface AuthorRepository extends JpaRepository<Author, Long>{

}

package uz.aminasaidakhmedova.library_project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.aminasaidakhmedova.library_project.model.Book;

public interface BookRepository extends JpaRepository<Book, Long> {
}

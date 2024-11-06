package uz.aminasaidakhmedova.library_project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import uz.aminasaidakhmedova.library_project.model.Book;
import java.util.Optional;

public interface BookRepository extends JpaRepository<Book, Long>, JpaSpecificationExecutor<Book> {
    Optional<Book> findBookByName(String name);

    @Query(nativeQuery = true, value = "Select * from book where name = ?")
    Optional<Book> findBookByNameBySql(String name);
}

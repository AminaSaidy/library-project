package uz.aminasaidakhmedova.library_project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.aminasaidakhmedova.library_project.model.User;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername (String username);
}

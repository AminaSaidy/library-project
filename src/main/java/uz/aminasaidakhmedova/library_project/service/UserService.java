package uz.aminasaidakhmedova.library_project.service;

import uz.aminasaidakhmedova.library_project.dto.UserCreateDto;
import uz.aminasaidakhmedova.library_project.dto.UserDto;
import uz.aminasaidakhmedova.library_project.dto.UserUpdateDto;
import uz.aminasaidakhmedova.library_project.model.User;
import java.util.List;

public interface UserService {
    User getUserByUsername(String username);
    UserDto getUserById(Long id);
    UserDto createUser(UserCreateDto userCreateDto);
    UserDto updateUser(UserUpdateDto userUpdateDto);
    void deleteUser(Long id);
    List<UserDto> getAllUsers();
}

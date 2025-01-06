package uz.aminasaidakhmedova.library_project.service;

import uz.aminasaidakhmedova.library_project.dto.UserCreateDto;
import uz.aminasaidakhmedova.library_project.dto.UserDto;
import uz.aminasaidakhmedova.library_project.dto.UserUpdateDto;
import java.util.List;

public interface UserService {
    UserDto getUserById(Long id);
    UserDto getUserByUsername(String username);
    UserDto createUser(UserCreateDto userCreateDto);
    UserDto updateUser(UserUpdateDto userUpdateDto);
    void deleteUser(Long id);
    List<UserDto> getAllUsers();
}

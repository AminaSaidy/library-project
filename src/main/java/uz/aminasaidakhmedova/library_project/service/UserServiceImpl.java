package uz.aminasaidakhmedova.library_project.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.aminasaidakhmedova.library_project.dto.UserCreateDto;
import uz.aminasaidakhmedova.library_project.dto.UserDto;
import uz.aminasaidakhmedova.library_project.dto.UserUpdateDto;
import uz.aminasaidakhmedova.library_project.model.User;
import uz.aminasaidakhmedova.library_project.repository.UserRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public UserDto getUserById(Long id) {
        User user = userRepository.findById(id).orElseThrow();
        return convertEntityToDto(user);
    }

    @Override
    public UserDto getUserByUsername(String username) {
        User user = userRepository.findByUsername(username).orElseThrow();
        return convertEntityToDto(user);
    }

    @Override
    public UserDto createUser(UserCreateDto userCreateDto) {
        User user = userRepository.save(convertDtoToEntity(userCreateDto));
        return convertEntityToDto(user);
    }

    @Override
    public UserDto updateUser(UserUpdateDto userUpdateDto) {
        User user = userRepository.findById(userUpdateDto.getId()).orElseThrow();
        user.setUsername(userUpdateDto.getUsername());
        user.setPassword(userUpdateDto.getPassword());
        user.setRole(userUpdateDto.getRole());
        User savedUser = userRepository.save(user);
        return convertEntityToDto(savedUser);
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public List<UserDto> getAllUsers() {
        List<User> users = userRepository.findAll();
        return users.stream().map(this::convertEntityToDto).collect(Collectors.toList());
    }

    private User convertDtoToEntity(UserCreateDto userCreateDto) {
        return User.builder()
                .username(userCreateDto.getUsername())
                .password(userCreateDto.getPassword())
                .role(userCreateDto.getRole())
                .build();
    }

    private UserDto convertEntityToDto(User user) {
        return UserDto.builder()
                .id(user.getId())
                .username(user.getUsername())
                .role(user.getRole())
                .build();
    }
}

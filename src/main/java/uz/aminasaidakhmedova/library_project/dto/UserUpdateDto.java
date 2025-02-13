package uz.aminasaidakhmedova.library_project.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class UserUpdateDto {
    private Long id;
    private String username;
    private String password;
    private String role;
}

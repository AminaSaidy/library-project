package uz.aminasaidakhmedova.library_project.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class BookCreateDto {
    @NotBlank(message = "Необходимо указать название")
    private String name;
    private Long genreId;
    private List<Long> authorIds;
}

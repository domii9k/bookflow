package edu.api.bookflow.DTO;

import edu.api.bookflow.Model.Autor;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;

public record AutorDTO(@Positive Long codAutor, @NotBlank String nome, @NotNull Boolean status) {

    public AutorDTO {
        if (status == null) {
            status = true;
        }
    }
}

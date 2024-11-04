package com.example.librarybooks.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import lombok.Data;

import java.time.LocalDate;

@Schema(description = "Книга")
@Data
public class BookDto {
    @Null(groups = {OnCreate.class})
    @NotNull(groups = {OnUpdate.class})
    @PositiveOrZero(groups = {OnUpdate.class})
    private Long id;
    @NotNull
    @NotEmpty
    @NotBlank
    @Size(max = 64)
    private String title;
    @NotNull
    @NotEmpty
    @NotBlank
    @Size(max = 64)
    private String author;
    @NotNull
    private LocalDate publishedDate;
}

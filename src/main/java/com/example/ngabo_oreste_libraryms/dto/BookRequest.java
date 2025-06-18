package com.example.ngabo_oreste_libraryms.dto;

import com.example.ngabo_oreste_libraryms.model.BookAvailabilityStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class BookRequest {
    @NotBlank(message = "Title is required and cannot be empty")
    @Size(min = 1, max = 255, message = "Title must be between 1 and 255 characters")
    @Pattern(regexp = "^[a-zA-Z0-9\\s\\-_,.!?'\"()]+$", message = "Title must contain only letters, numbers, spaces, and basic punctuation")
    private String title;

    @NotBlank(message = "Author is required and cannot be empty")
    @Size(min = 1, max = 255, message = "Author name must be between 1 and 255 characters")
    @Pattern(regexp = "^[a-zA-Z\\s\\-']+$", message = "Author name must contain only letters, spaces, hyphens, and apostrophes")
    private String author;

    @NotBlank(message = "ISBN is required and cannot be empty")
    @Pattern(regexp = "^[0-9\\-]+$", message = "ISBN must contain only numbers and hyphens")
    @Size(min = 10, max = 20, message = "ISBN must be between 10 and 20 characters")
    private String isbn;

    @NotNull(message = "Availability status is required")
    private BookAvailabilityStatus availabilityStatus;
}
package com.example.ngabo_oreste_libraryms.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class BorrowingRequest {
    @NotBlank(message = "ISBN is required and cannot be empty")
    @Pattern(regexp = "^[0-9\\-]+$", message = "ISBN must contain only numbers and hyphens")
    @Size(min = 10, max = 20, message = "ISBN must be between 10 and 20 characters")
    private String isbn;

    @NotBlank(message = "Borrower name is required and cannot be empty")
    @Size(min = 2, max = 100, message = "Borrower name must be between 2 and 100 characters")
    @Pattern(regexp = "^[a-zA-Z\\s\\-']+$", message = "Borrower name must contain only letters, spaces, hyphens, and apostrophes")
    private String borrowerName;
}
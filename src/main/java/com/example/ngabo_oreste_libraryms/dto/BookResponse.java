package com.example.ngabo_oreste_libraryms.dto;

import com.example.ngabo_oreste_libraryms.model.BookAvailabilityStatus;
import lombok.Data;

@Data
public class BookResponse {
    private Long id;
    private String title;
    private String author;
    private String isbn;
    private BookAvailabilityStatus availabilityStatus;
}
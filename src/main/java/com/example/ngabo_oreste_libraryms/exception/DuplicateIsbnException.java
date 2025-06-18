package com.example.ngabo_oreste_libraryms.exception;

public class DuplicateIsbnException extends RuntimeException {
    public DuplicateIsbnException(String isbn) {
        super("A book with ISBN '" + isbn + "' already exists. ISBNs must be unique.");
    }
}
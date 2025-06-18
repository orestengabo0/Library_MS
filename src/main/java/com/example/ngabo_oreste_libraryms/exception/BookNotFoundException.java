package com.example.ngabo_oreste_libraryms.exception;

public class BookNotFoundException extends RuntimeException {
    public BookNotFoundException(String isbn) {
        super("Book with ISBN '" + isbn + "' not found. Please check the ISBN and try again.");
    }
}
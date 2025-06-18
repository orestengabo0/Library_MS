package com.example.ngabo_oreste_libraryms.exception;

public class BookAlreadyBorrowedException extends RuntimeException {
    public BookAlreadyBorrowedException(String isbn) {
        super("The book with ISBN '" + isbn + "' is already borrowed and not available for borrowing.");
    }
}
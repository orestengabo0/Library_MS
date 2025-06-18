package com.example.ngabo_oreste_libraryms.service;

import com.example.ngabo_oreste_libraryms.dto.BookRequest;
import com.example.ngabo_oreste_libraryms.dto.BookResponse;
import com.example.ngabo_oreste_libraryms.model.Book;
import com.example.ngabo_oreste_libraryms.model.BookAvailabilityStatus;
import com.example.ngabo_oreste_libraryms.repository.BookRepository;
import com.example.ngabo_oreste_libraryms.exception.BookNotFoundException;
import com.example.ngabo_oreste_libraryms.exception.DuplicateIsbnException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;

    public BookResponse createBook(BookRequest request) {
        // Check if book with ISBN already exists
        if (bookRepository.existsByIsbn(request.getIsbn())) {
            throw new DuplicateIsbnException(request.getIsbn());
        }

        Book book = new Book();
        book.setTitle(request.getTitle());
        book.setAuthor(request.getAuthor());
        book.setIsbn(request.getIsbn());
        book.setAvailabilityStatus(request.getAvailabilityStatus());

        Book savedBook = bookRepository.save(book);
        return convertToResponse(savedBook);
    }

    public BookResponse getBookByIsbn(String isbn) {
        Optional<Book> book = bookRepository.findByIsbn(isbn);
        if (book.isEmpty()) {
            throw new BookNotFoundException(isbn);
        }
        return convertToResponse(book.get());
    }

    public BookAvailabilityStatus getBookAvailability(String isbn) {
        Optional<Book> book = bookRepository.findByIsbn(isbn);
        if (book.isEmpty()) {
            throw new BookNotFoundException(isbn);
        }
        return book.get().getAvailabilityStatus();
    }

    public Book getBookEntityByIsbn(String isbn) {
        Optional<Book> book = bookRepository.findByIsbn(isbn);
        if (book.isEmpty()) {
            throw new BookNotFoundException(isbn);
        }
        return book.get();
    }

    public void updateBookAvailability(Book book, BookAvailabilityStatus status) {
        book.setAvailabilityStatus(status);
        bookRepository.save(book);
    }

    private BookResponse convertToResponse(Book book) {
        BookResponse response = new BookResponse();
        response.setId(book.getId());
        response.setTitle(book.getTitle());
        response.setAuthor(book.getAuthor());
        response.setIsbn(book.getIsbn());
        response.setAvailabilityStatus(book.getAvailabilityStatus());
        return response;
    }
}
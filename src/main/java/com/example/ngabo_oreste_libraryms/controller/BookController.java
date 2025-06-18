package com.example.ngabo_oreste_libraryms.controller;

import com.example.ngabo_oreste_libraryms.dto.BookRequest;
import com.example.ngabo_oreste_libraryms.dto.BookResponse;
import com.example.ngabo_oreste_libraryms.model.BookAvailabilityStatus;
import com.example.ngabo_oreste_libraryms.service.BookService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/books")
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;

    @PostMapping
    public ResponseEntity<BookResponse> createBook(@Valid @RequestBody BookRequest request) {
        try {
            BookResponse response = bookService.createBook(request);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/{isbn}")
    public ResponseEntity<BookResponse> getBookByIsbn(@PathVariable String isbn) {
        try {
            BookResponse response = bookService.getBookByIsbn(isbn);
            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{isbn}/availability")
    public ResponseEntity<String> getBookAvailability(@PathVariable String isbn) {
        try {
            BookAvailabilityStatus status = bookService.getBookAvailability(isbn);
            return ResponseEntity.ok(status.toString());
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
package com.example.ngabo_oreste_libraryms.controller;

import com.example.ngabo_oreste_libraryms.dto.BorrowingRequest;
import com.example.ngabo_oreste_libraryms.dto.BorrowingResponse;
import com.example.ngabo_oreste_libraryms.service.BorrowingService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/borrowing")
@RequiredArgsConstructor
public class BorrowingController {

    private final BorrowingService borrowingService;

    @PostMapping
    public ResponseEntity<BorrowingResponse> createBorrowingTransaction(@Valid @RequestBody BorrowingRequest request) {
        try {
            BorrowingResponse response = borrowingService.createBorrowingTransaction(request);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/return/{isbn}")
    public ResponseEntity<BorrowingResponse> returnBook(@PathVariable String isbn) {
        try {
            BorrowingResponse response = borrowingService.returnBook(isbn);
            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        }
    }
}
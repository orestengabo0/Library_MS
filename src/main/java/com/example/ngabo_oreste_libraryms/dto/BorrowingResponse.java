package com.example.ngabo_oreste_libraryms.dto;

import com.example.ngabo_oreste_libraryms.model.TransactionStatus;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class BorrowingResponse {
    private Long id;
    private String bookTitle;
    private String bookIsbn;
    private String borrowerName;
    private LocalDateTime borrowDate;
    private LocalDateTime returnDate;
    private TransactionStatus status;
}
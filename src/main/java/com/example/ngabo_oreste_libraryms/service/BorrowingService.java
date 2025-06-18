package com.example.ngabo_oreste_libraryms.service;

import com.example.ngabo_oreste_libraryms.dto.BorrowingRequest;
import com.example.ngabo_oreste_libraryms.dto.BorrowingResponse;
import com.example.ngabo_oreste_libraryms.model.Book;
import com.example.ngabo_oreste_libraryms.model.BookAvailabilityStatus;
import com.example.ngabo_oreste_libraryms.model.BorrowingTransaction;
import com.example.ngabo_oreste_libraryms.model.TransactionStatus;
import com.example.ngabo_oreste_libraryms.repository.BorrowingTransactionRepository;
import com.example.ngabo_oreste_libraryms.exception.BookAlreadyBorrowedException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BorrowingService {

    private final BorrowingTransactionRepository borrowingTransactionRepository;
    private final BookService bookService;

    @Transactional
    public BorrowingResponse createBorrowingTransaction(BorrowingRequest request) {
        Book book = bookService.getBookEntityByIsbn(request.getIsbn());

        if (book.getAvailabilityStatus() != BookAvailabilityStatus.AVAILABLE) {
            throw new BookAlreadyBorrowedException(request.getIsbn());
        }

        BorrowingTransaction transaction = new BorrowingTransaction();
        transaction.setBook(book);
        transaction.setBorrowerName(request.getBorrowerName());
        transaction.setBorrowDate(LocalDateTime.now());
        transaction.setStatus(TransactionStatus.PENDING);

        BorrowingTransaction savedTransaction = borrowingTransactionRepository.save(transaction);
        bookService.updateBookAvailability(book, BookAvailabilityStatus.BORROWED);

        return convertToResponse(savedTransaction);
    }

    @Transactional
    public BorrowingResponse returnBook(String isbn) {
        Book book = bookService.getBookEntityByIsbn(isbn);

        Optional<BorrowingTransaction> pendingTransaction = borrowingTransactionRepository.findByBookAndStatus(book,
                TransactionStatus.PENDING);

        if (pendingTransaction.isEmpty()) {
            throw new RuntimeException("No pending borrowing transaction found for book with ISBN: " + isbn);
        }

        BorrowingTransaction transaction = pendingTransaction.get();
        transaction.setReturnDate(LocalDateTime.now());
        transaction.setStatus(TransactionStatus.RETURNED);

        BorrowingTransaction savedTransaction = borrowingTransactionRepository.save(transaction);
        bookService.updateBookAvailability(book, BookAvailabilityStatus.AVAILABLE);

        return convertToResponse(savedTransaction);
    }

    private BorrowingResponse convertToResponse(BorrowingTransaction transaction) {
        BorrowingResponse response = new BorrowingResponse();
        response.setId(transaction.getId());
        response.setBookTitle(transaction.getBook().getTitle());
        response.setBookIsbn(transaction.getBook().getIsbn());
        response.setBorrowerName(transaction.getBorrowerName());
        response.setBorrowDate(transaction.getBorrowDate());
        response.setReturnDate(transaction.getReturnDate());
        response.setStatus(transaction.getStatus());
        return response;
    }
}
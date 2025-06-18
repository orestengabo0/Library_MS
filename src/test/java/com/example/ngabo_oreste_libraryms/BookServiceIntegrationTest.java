package com.example.ngabo_oreste_libraryms;

import com.example.ngabo_oreste_libraryms.model.Book;
import com.example.ngabo_oreste_libraryms.model.BookAvailabilityStatus;
import com.example.ngabo_oreste_libraryms.repository.BookRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
@Transactional
class BookServiceIntegrationTest {

    @Autowired
    private BookRepository bookRepository;

    @Test
    void testBookCreation() {
        // Test book creation functionality
        Book book = new Book();
        book.setTitle("Test Book");
        book.setAuthor("Test Author");
        book.setIsbn("1234567890");
        book.setAvailabilityStatus(BookAvailabilityStatus.AVAILABLE);

        Book savedBook = bookRepository.save(book);

        assertNotNull(savedBook.getId());
        assertEquals("Test Book", savedBook.getTitle());
        assertEquals("Test Author", savedBook.getAuthor());
        assertEquals("1234567890", savedBook.getIsbn());
        assertEquals(BookAvailabilityStatus.AVAILABLE, savedBook.getAvailabilityStatus());
    }

    @Test
    void testBookRetrieval() {
        // First create a book
        Book book = new Book();
        book.setTitle("Retrieval Test Book");
        book.setAuthor("Test Author");
        book.setIsbn("9876543210");
        book.setAvailabilityStatus(BookAvailabilityStatus.AVAILABLE);

        bookRepository.save(book);

        // Then retrieve it by ISBN
        var foundBook = bookRepository.findByIsbn("9876543210");
        assertTrue(foundBook.isPresent());
        assertEquals("Retrieval Test Book", foundBook.get().getTitle());
    }
}
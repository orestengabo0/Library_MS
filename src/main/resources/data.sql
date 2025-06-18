-- Sample data for Library Management System
-- This script will be executed automatically by Spring Boot

-- Insert sample books
INSERT INTO books (title, author, isbn, availability_status) VALUES
('The Great Gatsby', 'F. Scott Fitzgerald', '978-0743273565', 'AVAILABLE'),
('To Kill a Mockingbird', 'Harper Lee', '978-0446310789', 'AVAILABLE'),
('1984', 'George Orwell', '978-0451524935', 'AVAILABLE'),
('Pride and Prejudice', 'Jane Austen', '978-0141439518', 'AVAILABLE'),
('The Hobbit', 'J.R.R. Tolkien', '978-0547928241', 'AVAILABLE')
ON CONFLICT (isbn) DO NOTHING; 
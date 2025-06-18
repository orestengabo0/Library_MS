# Library Management System

A comprehensive library management system built with Spring Boot, Hibernate/JPA, and PostgreSQL. The system provides RESTful API endpoints for managing books, borrowing transactions, and tracking borrowing history.

## Features

- **Book Management**: Create and retrieve books with unique ISBNs
- **Borrowing System**: Borrow and return books with automatic availability tracking
- **Transaction History**: Track all borrowing transactions with status updates
- **RESTful API**: Complete REST API for all operations
- **Data Validation**: Input validation and error handling
- **Database Persistence**: PostgreSQL with Hibernate/JPA

## Technology Stack

- **Spring Boot 3.5.0**
- **Spring Data JPA**
- **Hibernate**
- **PostgreSQL**
- **Lombok**
- **Maven**

## Prerequisites

- Java 17 or higher
- Maven 3.6+
- PostgreSQL 12+

## Database Setup

1. Create a PostgreSQL database named `libraryms`
2. Update the database credentials in `src/main/resources/application.properties`:
   ```properties
   spring.datasource.username=your_username
   spring.datasource.password=your_password
   ```

## Running the Application

1. Clone the repository
2. Navigate to the project directory
3. Run the application:
   ```bash
   mvn spring-boot:run
   ```
4. The application will start on `http://localhost:8080`

## API Endpoints

### Book Management

#### Create a New Book
- **POST** `/api/books`
- **Request Body:**
  ```json
  {
    "title": "The Great Gatsby",
    "author": "F. Scott Fitzgerald",
    "isbn": "978-0743273565",
    "availabilityStatus": "AVAILABLE"
  }
  ```
- **Response:** Created book details with ID

#### Get Book by ISBN
- **GET** `/api/books/{isbn}`
- **Response:**
  ```json
  {
    "id": 1,
    "title": "The Great Gatsby",
    "author": "F. Scott Fitzgerald",
    "isbn": "978-0743273565",
    "availabilityStatus": "AVAILABLE"
  }
  ```

#### Check Book Availability
- **GET** `/api/books/{isbn}/availability`
- **Response:** `"AVAILABLE"` or `"BORROWED"`

### Borrowing Management

#### Create Borrowing Transaction
- **POST** `/api/borrowing`
- **Request Body:**
  ```json
  {
    "isbn": "978-0743273565",
    "borrowerName": "John Doe"
  }
  ```
- **Response:**
  ```json
  {
    "id": 1,
    "bookTitle": "The Great Gatsby",
    "bookIsbn": "978-0743273565",
    "borrowerName": "John Doe",
    "borrowDate": "2024-01-15T10:30:00",
    "returnDate": null,
    "status": "PENDING"
  }
  ```

#### Return Book
- **PUT** `/api/borrowing/return/{isbn}`
- **Response:**
  ```json
  {
    "id": 1,
    "bookTitle": "The Great Gatsby",
    "bookIsbn": "978-0743273565",
    "borrowerName": "John Doe",
    "borrowDate": "2024-01-15T10:30:00",
    "returnDate": "2024-01-20T14:30:00",
    "status": "RETURNED"
  }
  ```

## Database Schema

### Books Table
- `id` (PK, Long)
- `title` (String, not null)
- `author` (String, not null)
- `isbn` (String, unique, not null)
- `availability_status` (Enum: AVAILABLE, BORROWED)

### Borrowing Transactions Table
- `id` (PK, Long)
- `book_id` (FK to books.id, not null)
- `borrower_name` (String, not null)
- `borrow_date` (LocalDateTime, not null)
- `return_date` (LocalDateTime, nullable)
- `status` (Enum: PENDING, RETURNED)

## Business Logic

1. **Book Availability Check**: Before borrowing, the system verifies the book is available
2. **Automatic Status Updates**: Book availability is automatically updated when borrowed or returned
3. **Transaction Tracking**: All borrowing transactions are tracked with timestamps
4. **Unique ISBN**: Each book must have a unique ISBN
5. **Validation**: Input validation ensures data integrity

## Error Handling

The system provides comprehensive error handling:
- Validation errors for invalid input
- Business logic errors (e.g., book not available)
- Database constraint violations
- Resource not found errors

## Testing the API

You can test the API using tools like:
- Postman
- cURL
- Any REST client

### Example cURL Commands

```bash
# Create a book
curl -X POST http://localhost:8080/api/books \
  -H "Content-Type: application/json" \
  -d '{"title":"Test Book","author":"Test Author","isbn":"1234567890","availabilityStatus":"AVAILABLE"}'

# Get book by ISBN
curl http://localhost:8080/api/books/1234567890

# Check availability
curl http://localhost:8080/api/books/1234567890/availability

# Borrow a book
curl -X POST http://localhost:8080/api/borrowing \
  -H "Content-Type: application/json" \
  -d '{"isbn":"1234567890","borrowerName":"John Doe"}'

# Return a book
curl -X PUT http://localhost:8080/api/borrowing/return/1234567890
```

## Project Structure

```
src/main/java/com/example/ngabo_oreste_libraryms/
├── controller/
│   ├── BookController.java
│   └── BorrowingController.java
├── dto/
│   ├── BookRequest.java
│   ├── BookResponse.java
│   ├── BorrowingRequest.java
│   └── BorrowingResponse.java
├── exception/
│   └── GlobalExceptionHandler.java
├── model/
│   ├── Book.java
│   ├── BookAvailabilityStatus.java
│   ├── BorrowingTransaction.java
│   └── TransactionStatus.java
├── repository/
│   ├── BookRepository.java
│   └── BorrowingTransactionRepository.java
├── service/
│   ├── BookService.java
│   └── BorrowingService.java
└── LibraryMsApplication.java
```

## Contributing

1. Fork the repository
2. Create a feature branch
3. Make your changes
4. Add tests if applicable
5. Submit a pull request

## License

This project is licensed under the MIT License. 
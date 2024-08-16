package com.win.junit;
import org.example.Book;
import org.example.BookService;
import org.example.User;
import org.junit.Test;
import org.junit.Before;
import org.mockito.Mockito;

import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class BookServiceTest {

    private BookService bookService;
    private User mockUser;
    private Book mockBook;

    @Before
    public void setUp() {
        bookService = new BookService();
        mockUser = mock(User.class);

        // Assuming the Book class has a default constructor
        mockBook = new Book("Test Title", "Test Author", "Test Genre", 19.99);


        // Add the mock book to the database for testing purposes
        bookService.addBook(mockBook);
    }

    //test for searching book
    @Test
    public void testSearchBook_Success(){
        List<Book> result = bookService.searchBook("Test Title");
        assertFalse(result.isEmpty());
        assertEquals(1, result.size());
        assertEquals("Test Title", result.get(0).getTitle());
    }

    @Test
    public void testSearchBook_Fail(){
        List<Book> result = bookService.searchBook("Test Title Not Found");
        assertTrue(result.isEmpty());
    }

    @Test
    public void testSearchBook_EmptyKeyword(){
        List<Book> result = bookService.searchBook("");
        assertFalse(result.isEmpty());// assuming return all books
    }

    //Test for purchase Book
    @Test
    public void testPurchaseBook_Success(){
        boolean result = bookService.purchaseBook(mockUser, mockBook);
        assertTrue(result);//pos case
    }

    @Test
    public void testPurchaseBook_BookNotAvailable() {
        // Creating a Book object with all required constructor arguments
        Book nonExistentBook = new Book("NonExistent Title", "NonExistent Author", "NonExistent Genre", 29.99);

        // Testing if the purchaseBook method correctly identifies that the book is not available
        boolean result = bookService.purchaseBook(mockUser, nonExistentBook);

        // Asserting that the result is false since the book is not in the database
        assertFalse(result); // Negative case
    }

    @Test
    public void testPurchaseBook_NullBook(){
        boolean result = bookService.purchaseBook(mockUser, null);
        assertFalse(result);//edge case

    }
    //test for reviews
    @Test
    public void testAddBookReview_Success() {
        when(mockUser.getPurchasedBooks()).thenReturn(List.of(mockBook));
        boolean result = bookService.addBookReview(mockUser, mockBook, "Great Book!");
        assertTrue(result); // Positive case
    }
    @Test
    public void testAddBookReview_UserHasNotPurchasedBook() {
        when(mockUser.getPurchasedBooks()).thenReturn(List.of(new Book("Test Title", "Test Author", "Test Genre", 19.99)));
        boolean result = bookService.addBookReview(mockUser, mockBook, "Great Book!");
        assertFalse(result);//neg case
    }
    @Test
    public void testAddBookReview_NullReview() {
        when(mockUser.getPurchasedBooks()).thenReturn(List.of(mockBook));
        boolean result = bookService.addBookReview(mockUser, mockBook, null);
        assertTrue(result);

    }
}

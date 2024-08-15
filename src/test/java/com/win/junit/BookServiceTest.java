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
        mockBook = new Book("Test Title", "Test Author", "Test Genre");

        //Add mockbook to database for testin
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
    public void testPurchaseBook_BookNotAvailable(){
        Book nonExistentBook = new Book("NonExistent Title", "NonExistent Author", "NonExistent Genre");
        boolean result = bookService.purchaseBook(mockUser, nonExistentBook);
        assertFalse(result);//neg case
    }

    @Test
    public void testPurchaseBook_NullBook(){
        boolean result = bookService.purchaseBook(mockUser, null);
        assertFalse(result);//edge case

    }
    @Test
    public void testAddBookReview_Success() {
        when(mockUser.getPurchasedBooks()).thenReturn(List.of(mockBook));
        boolean result = bookService.addBookReview(mockUser, mockBook, "Great Book!");
        assertTrue(result); // Positive case
    }
    @Test
    public void testAddBookReview_NullReview() {

    }
}

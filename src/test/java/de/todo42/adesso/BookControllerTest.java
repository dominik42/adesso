package de.todo42.adesso;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import de.todo42.adesso.book.Book;
import de.todo42.adesso.book.BookRestController;
import de.todo42.adesso.book.BookService;

@RunWith(MockitoJUnitRunner.class)
public class BookControllerTest {

    @Mock
    private BookService bookService;
    
    private BookRestController bookController;
    
    @Before
    public void setup() {
        List<Book> books = Arrays.asList(ValueStore.book());
        when(bookService.loadAllBooks()).thenReturn(books);
    }
    
    @Test
    public void testGetAllBooks() throws Exception {
        bookController = new BookRestController(bookService);
        Collection<Book> books = bookController.getAllBooks();
        assertEquals(1,  books.size());
    }
}
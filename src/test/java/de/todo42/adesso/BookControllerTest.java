package de.todo42.adesso;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.fasterxml.jackson.databind.ObjectMapper;

import de.todo42.adesso.book.Book;
import de.todo42.adesso.book.BookRestController;
import de.todo42.adesso.book.BookService;
import de.todo42.adesso.book.BookValidator;
import de.todo42.adesso.book.Publisher;

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
        bookController = new BookRestController(bookService, new BookValidator());

        List<Book> books = new ArrayList<>();
        bookController.getAllBooks().forEach(books::add);
        assertEquals(1,  books.size());
    }
    
    @Test
    public void testPublisher() throws Exception {
        Publisher p = new Publisher();
        p.setName("P1");
        
        Book b1 = ValueStore.book();
        Book b2 = ValueStore.book();
        
        p.addBook(b1);
        p.addBook(b2);
        
        ObjectMapper mapper = new ObjectMapper();
        System.out.println(mapper.writeValueAsString(p));
        
        
    }
}

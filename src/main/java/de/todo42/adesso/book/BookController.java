package de.todo42.adesso.book;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(BookController.REQUEST_URL)
public class BookController {

    public static final String REQUEST_URL = "/book";
    
    @GetMapping
    public Book getAllBooks() {
        Book book = Book.builder()
                .title("Spring Boot 2")
                .author("Michael Simons")
                .isbn("1234567890123")
                .build();
        return book;
    }
    
    @GetMapping(path = "/{isbn}")
    public Book getSingleBook(@PathVariable(name = "isbn", required = true)
            String isbn) {
        Book book = Book.builder()
                .title("Spring Boot 2")
                .author("Michael Simons")
                .isbn(isbn)
                .build();
        return book;
    }

    @GetMapping(params = "isbn")
    public Book getSingleBook2(@RequestParam(name = "isbn", required = true)
    String isbn) {
        Book book = Book.builder()
                .title("Spring Boot 2")
                .author("Michael Simons")
                .isbn(isbn)
                .build();
        return book;
    }
    
    
}

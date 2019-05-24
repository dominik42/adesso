package de.todo42.adesso.book;

import java.util.Collection;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(BookRestController.REQUEST_URL)
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class BookRestController {

    public static final String REQUEST_URL = "/book";
    
    @NonNull
    private BookService bookService;
    
    @NonNull
    private BookValidator bookValidator;
    
    
    @InitBinder
    public void initBinding(WebDataBinder webDataBinder) {
        webDataBinder.addValidators(bookValidator);
    }
    
    @GetMapping
    public Collection<Book> getAllBooks() {
        return bookService.loadAllBooks();
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

    @PostMapping
    public Book createBook(@Valid @RequestBody(required = true) Book book) {
        bookService.addBook(book);
        return book;
    }
    
    
}

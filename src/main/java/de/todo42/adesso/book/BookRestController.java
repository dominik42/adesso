package de.todo42.adesso.book;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
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
    public List<Book> getAllBooks() {
        return bookService.loadAllBooks();
    }
    
    @GetMapping(path = "/{isbn}")
    public Book getSingleBook(@PathVariable(name = "isbn", required = true)
            String isbn) {
        return bookService.loadByIsbn(isbn);
    }

    @PostMapping
    public Book createBook(@Valid @RequestBody(required = true) Book book,
            BindingResult bindingResult) {
        if (bindingResult.hasFieldErrors()) {
            FieldError error = bindingResult.getFieldErrors().get(0);
            throw new BookException(error.getField(), error.getCode(), error.getArguments()); 
        }
        bookService.addBook(book);
        return book;
    }
    
    @ExceptionHandler
    public ResponseEntity<ErrorResponse> exceptionHandler(BookException bookException) {
        ErrorResponse error = new ErrorResponse(bookException.getField(), bookException.getCode(), bookException.getArguments());
        
        ResponseEntity<ErrorResponse> response = new ResponseEntity<ErrorResponse>(error, HttpStatus.BAD_REQUEST);
        return response;
               
    }
    
    
}

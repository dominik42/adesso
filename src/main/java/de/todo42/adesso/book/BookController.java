package de.todo42.adesso.book;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class BookController {

    
    @NonNull
    private BookService bookService;
    
    
    @GetMapping(path = "/")
    public String index(Model model) {
        model.addAttribute("message", "Hallo adesso");
        return "index";
    }
    
    @GetMapping(path = "/books")
    public String books(Model model) {
        List<Book> books = new ArrayList<Book>();
        books.addAll(bookService.loadAllBooks());
        model.addAttribute("books", books);
        return "books";
    }

    @GetMapping(path = "/newBook")
    public String newBook(Model model) {
        model.addAttribute("book", new Book());
        return "newBook";
    }

    @PostMapping(path = "/createBook")
    public String createBook(Book book, Model model) {
        bookService.addBook(book);
        return "redirect:books";
    }
    
}

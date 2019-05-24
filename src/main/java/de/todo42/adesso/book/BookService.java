package de.todo42.adesso.book;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class BookService {
    
    @NonNull
    private BookRepository bookRepository;
    
    public List<Book> loadAllBooks() {
        return bookRepository.findAll();
    }

    public void addBook(Book book) {
        bookRepository.save(book);
    }
    
    public Book loadByIsbn(String isbn) {
        return bookRepository.findByIsbn(isbn);
    }

    
}

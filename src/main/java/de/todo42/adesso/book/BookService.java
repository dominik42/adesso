package de.todo42.adesso.book;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class BookService {
    
    @NonNull
    private BookRepository bookRepository;
    
    @NonNull
    private JdbcTemplate jdbcTemplate;
    
    
    
    public List<Book> loadAllBooks() {
        return bookRepository.findAll();
    }

    public void addBook(Book book) {
        bookRepository.save(book);
    }
    
    public Book loadByIsbn(String isbn) {
//        String sql = "SELECT * FROm books WHERE isbn = '" + isbn + "'";
//        Book book = jdbcTemplate.queryForObject(sql, Book.class);
        return bookRepository.getByIsbn(isbn);
        //return book;
    }

    
}

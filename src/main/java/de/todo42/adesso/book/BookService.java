package de.todo42.adesso.book;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
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
        String sql = "SELECT * FROM books WHERE isbn = '" + isbn + "'";
        Book book = jdbcTemplate.queryForObject(sql, new RowMapper<Book>() {

            @Override
            public Book mapRow(ResultSet rs, int rowNum) throws SQLException {
                Book book = Book.builder()
                        .title(rs.getString("title"))
                        .author(rs.getString("author"))
                        .isbn(rs.getString("isbn"))
                        .build();
                return book;
            }
            
        });
        return book;
        //return bookRepository.getByIsbn(isbn);
    }

    
}

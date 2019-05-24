package de.todo42.adesso.book;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends CrudRepository<Book, Integer> {

    List<Book> findAll();
    
    Book findByIsbn(String isbn);
}

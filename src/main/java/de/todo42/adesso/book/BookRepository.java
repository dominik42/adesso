package de.todo42.adesso.book;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends CrudRepository<Book, Integer> {

    List<Book> findAll();
    
    @Query(nativeQuery = true, value = "SELECT * from books WHERE isbn = :isbn")
    Book getByIsbn(@Param("isbn")String isbn);
    
    
}

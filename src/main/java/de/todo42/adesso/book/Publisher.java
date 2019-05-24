package de.todo42.adesso.book;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.Data;

@Entity
@Table(name = "publisher")
@Data
public class Publisher {

    @Id
    private Integer id;
    
    private String name;
    
    @OneToMany
    @JsonManagedReference
    private List<Book> books = new ArrayList<Book>();

    public void addBook(Book b1) {
        books.add(b1);
        //b1.setPublisher(this);
        
    }
}

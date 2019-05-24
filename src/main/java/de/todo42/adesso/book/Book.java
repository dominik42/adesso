package de.todo42.adesso.book;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Book {

    @NotNull
    private String title;
    
    private String author;
    
    @Size(min = 13, max = 13)
    private String isbn;
    
}

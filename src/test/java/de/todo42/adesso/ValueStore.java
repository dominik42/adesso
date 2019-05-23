package de.todo42.adesso;

import de.todo42.adesso.book.Book;

public class ValueStore {

    public static Book book() {
        Book book = Book.builder()
                .title("Spring Boot 2")
                .author("Michael Simons")
                .isbn("1234567890123")
                .build();
        return book;
    }
}

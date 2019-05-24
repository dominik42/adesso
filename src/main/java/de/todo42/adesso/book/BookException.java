package de.todo42.adesso.book;

import lombok.Data;

@Data
public class BookException extends RuntimeException {

    private String field;
    private String code;
    private Object[] arguments;
    
    
    public BookException(String field, String code, Object[] arguments) {
        this.field = field;
        this.code = code;
        this.arguments = arguments;
    }

}

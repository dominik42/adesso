package de.todo42.adesso.book;

import lombok.Value;

@Value
public class ErrorResponse {

    private String field;
    private String code;
    private Object[] arguments;
    

}

package de.todo42.adesso.book;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class BookValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return clazz.isAssignableFrom(Book.class);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Book book = (Book) target;
        
        validateTitle(book, errors);
        
    }

    private void validateTitle(Book book, Errors errors) {
        if (! (book.getTitle() != null && book.getTitle().equalsIgnoreCase("111"))) {
            errors.rejectValue("title", ErrorCodes.WRONG_TITLE.name(), new Object[] {book.getTitle()}, "bookm title wrong");
        }
        
    }

}

package org.itstep.msk.app.exeption;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class CategoryExceptionHandler {

    @ExceptionHandler(CategoryException.class)
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    public ResponseEntity<CategoryException> handleCategoryException(CategoryException cEx) {
        return ResponseEntity.unprocessableEntity().body(cEx);
    }
}

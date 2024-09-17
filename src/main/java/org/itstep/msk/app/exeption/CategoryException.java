package org.itstep.msk.app.exeption;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class CategoryException extends RuntimeException{

    private String message;
    private HttpStatus status;

    public CategoryException(String message) {
        super(message);

    }

}

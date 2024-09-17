package org.itstep.msk.app.exeption;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class CustomerException extends RuntimeException {

    private final String message;
    private final HttpStatus status;

    public CustomerException(String message, HttpStatus status) {
        this.message = message;
        this.status = status;
    }

    public static CustomerException creteException(String msg) {
        return new CustomerException(msg, HttpStatus.NOT_FOUND);
    }
}
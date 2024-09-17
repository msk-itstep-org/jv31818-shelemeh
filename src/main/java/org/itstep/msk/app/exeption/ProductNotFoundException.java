package org.itstep.msk.app.exeption;

public class ProductNotFoundException extends RuntimeException {

    ProductNotFoundException() {
        super();
    }

    public ProductNotFoundException(String msg, Throwable cause) {
        super(msg, cause);
    }

    public ProductNotFoundException(String msg) {
        super(msg);
    }

    public ProductNotFoundException(Throwable cause) {
        super(cause);
    }
}

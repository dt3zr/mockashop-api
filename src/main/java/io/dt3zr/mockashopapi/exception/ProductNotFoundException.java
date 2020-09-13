package io.dt3zr.mockashopapi.exception;

public class ProductNotFoundException extends BaseMockashopServiceException {
    public ProductNotFoundException(String message) {
        super(message);
    }
}

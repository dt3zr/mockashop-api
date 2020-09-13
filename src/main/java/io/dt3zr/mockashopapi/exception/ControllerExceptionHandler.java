package io.dt3zr.mockashopapi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class ControllerExceptionHandler {
    @ResponseStatus(value = HttpStatus.NOT_ACCEPTABLE)
    @ExceptionHandler(value = ProductExistsException.class)
    public @ResponseBody ErrorMessage handleProductExistsException(HttpServletRequest request, Exception exception) {
        return new ErrorMessage(exception.getMessage(), HttpStatus.NOT_ACCEPTABLE);
    }

    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    @ExceptionHandler(value = ProductNotFoundException.class)
    public @ResponseBody ErrorMessage handleProductNotFoundException(HttpServletRequest request, Exception exception) {
        return new ErrorMessage(exception.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    @ExceptionHandler(value = ShoppingCartNotFoundException.class)
    public @ResponseBody ErrorMessage handleShoppingCartNotFoundException(HttpServletRequest request, Exception exception) {
        return new ErrorMessage(exception.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    @ExceptionHandler(value = OrderNotFoundException.class)
    public @ResponseBody ErrorMessage handleOrderNotFoundException(HttpServletRequest request, Exception exception) {
        return new ErrorMessage(exception.getMessage(), HttpStatus.NOT_FOUND);
    }
}

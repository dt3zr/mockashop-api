package io.dt3zr.mockashopapi.exception;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Data
@RequiredArgsConstructor
public class ErrorMessage {
    private final String error;
    private final HttpStatus status;
}

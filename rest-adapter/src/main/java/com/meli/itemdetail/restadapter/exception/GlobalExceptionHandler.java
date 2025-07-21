package com.meli.itemdetail.restadapter.exception;

import com.meli.itemdetail.core.domain.dto.Cause;
import com.meli.itemdetail.core.domain.dto.ErrorResponse;
import com.meli.itemdetail.core.domain.exception.NotFoundException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex,
            HttpHeaders headers,
            HttpStatusCode status,
            WebRequest request) {

        List<Cause> causes = buildValidationCauses(ex);
        ErrorResponse errorResponse = new ErrorResponse(
                "Validation error",
                "validation_error",
                400,
                causes
        );

        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ErrorResponse> handleNotFoundException(NotFoundException ex) {
        Cause cause = buildNotFoundCause(ex);
        ErrorResponse errorResponse = new ErrorResponse(
                "Resource not found",
                "not_found",
                404,
                List.of(cause)
        );

        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGenericException(Exception ex) {
        Cause cause = buildInternalErrorCause();
        ErrorResponse errorResponse = new ErrorResponse(
                "Unexpected error",
                "internal_error",
                500,
                List.of(cause)
        );

        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private List<Cause> buildValidationCauses(MethodArgumentNotValidException ex) {
        return ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(fieldError -> new Cause(
                        "items",
                        400,
                        "error",
                        "validation_error",
                        List.of(fieldError.getField()),
                        fieldError.getDefaultMessage()
                ))
                .collect(Collectors.toList());
    }

    private Cause buildNotFoundCause(NotFoundException ex) {
        return new Cause(
                "items",
                404,
                "error",
                ex.getErrorCode(),
                ex.getFieldReference(),
                ex.getMessage()
        );
    }

    private Cause buildInternalErrorCause() {
        return new Cause(
                "items",
                500,
                "error",
                "internal_error",
                List.of(),
                "Unexpected error"
        );
    }
}

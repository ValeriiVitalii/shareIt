package ru.practicum.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ErrorHandler {

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse validation(final ValidationException e) {
        return new ErrorResponse("Указано некорректное значение", e.getMessage());
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.CONFLICT)
    public ErrorResponse validation(final DuplicationException e) {
        return new ErrorResponse("Имеется дубликат", e.getMessage());
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse validation(final NotFoundException e) {
        return new ErrorResponse("Сущность не найдена", e.getMessage());
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorResponse validation(final StatusException e) {
        return new ErrorResponse("Unknown state: UNSUPPORTED_STATUS", e.getMessage());
    }
}

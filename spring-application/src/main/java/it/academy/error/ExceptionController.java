package it.academy.error;

import it.academy.error.dto.ErrorDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.MessageSourceResolvable;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.validation.method.MethodValidationException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.UUID;

@RestControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
public class ExceptionController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ExceptionController.class);

    @ExceptionHandler({Exception.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorDto handlerGlobalException(final Exception ex) {
        final UUID errorId = UUID.randomUUID();
        LOGGER.error("[%s} Server error".formatted(errorId), ex);

        return new ErrorDto(
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                "Server error, please try again",
                errorId
        );
    }

    @ExceptionHandler({
            MethodValidationException.class,
            MethodArgumentNotValidException.class
    })
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorDto handlerLocalException(final Exception ex) {
        if (ex instanceof MethodValidationException) {
            return ((MethodValidationException) ex).getAllErrors().stream()
                    .map(MessageSourceResolvable::getDefaultMessage)
                    .reduce((x, y) -> x + ", " + y)
                    .map(message -> new ErrorDto(HttpStatus.BAD_REQUEST.value(), message))
                    .orElse(new ErrorDto(HttpStatus.BAD_REQUEST.value(), "validation error"));
        } else {
            return new ErrorDto(
                    HttpStatus.BAD_REQUEST.value(),
                    ((MethodArgumentNotValidException) ex).getAllErrors().stream()
                            .map(DefaultMessageSourceResolvable::getDefaultMessage)
                            .reduce((x, y) -> x + ", " + y)
                            .orElse(null)
            );
        }
    }
}

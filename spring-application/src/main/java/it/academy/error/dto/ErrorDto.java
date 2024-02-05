package it.academy.error.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.UUID;

public class ErrorDto {

    private final int code;

    private final String message;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private final UUID errorId;

    public ErrorDto(int code, String message) {
        this.code = code;
        this.message = message;
        this.errorId = null;
    }

    public ErrorDto(int code, String message, UUID errorId) {
        this.code = code;
        this.message = message;
        this.errorId = errorId;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public UUID getErrorId() {
        return errorId;
    }
}

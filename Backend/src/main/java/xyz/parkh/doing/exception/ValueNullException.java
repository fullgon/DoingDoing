package xyz.parkh.doing.exception;

import lombok.Getter;

@Getter
public class ValueNullException extends RuntimeException {
    String message;

    public ValueNullException(String message) {
        this.message = message;
    }
}

package xyz.parkh.doing.exception;

import lombok.Getter;

@Getter
public class RequiredValueNullException extends RuntimeException {
    String message;

    public RequiredValueNullException(String message) {
        this.message = message;
    }
}

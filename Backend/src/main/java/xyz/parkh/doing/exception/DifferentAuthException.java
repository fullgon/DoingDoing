package xyz.parkh.doing.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class DifferentAuthException extends RuntimeException {
    String message;
}

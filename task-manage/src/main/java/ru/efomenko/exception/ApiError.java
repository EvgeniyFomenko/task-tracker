package ru.efomenko.exception;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class ApiError {
    private String message;
    private String timestamp;
    private String status;
    private String reason;
}

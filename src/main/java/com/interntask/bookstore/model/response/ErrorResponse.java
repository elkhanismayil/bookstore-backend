package com.interntask.bookstore.model.response;

import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;
@Data
@Builder
public class ErrorResponse {
    private String message;
    private HttpStatus status;
}

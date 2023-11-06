package com.interntask.bookstore.model.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateAuthorRequest {
    @NotEmpty(message = "Name cannot be empty")
    private String name;
    @NotNull(message = "Age cannot be null")
    @Positive(message = "Age must be positive digit")
    @Min(value = 10, message = "Age must be over 10 years old")
    private Integer age;
    private List<@Valid CreateBookRequest> books;
}

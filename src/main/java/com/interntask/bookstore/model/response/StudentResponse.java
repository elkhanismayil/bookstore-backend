package com.interntask.bookstore.model.response;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StudentResponse {
    private Long id;
    private String name;
    private Integer age;
    private List<BookResponse> books;
}

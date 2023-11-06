package com.interntask.bookstore.model.response;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AuthorResponse {
    private Long id;
    private String authorName;
    private Integer authorAge;
    private List<BookResponse> books;
}

package com.interntask.bookstore.model.response;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class BookResponse {
    private Long id;
    private String bookName;
}

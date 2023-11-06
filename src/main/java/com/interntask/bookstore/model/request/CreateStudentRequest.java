package com.interntask.bookstore.model.request;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateStudentRequest {
    private String name;
    private Integer age;
}

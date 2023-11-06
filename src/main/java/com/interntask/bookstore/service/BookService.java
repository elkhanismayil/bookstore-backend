package com.interntask.bookstore.service;

import com.interntask.bookstore.model.response.BookResponse;

import java.util.List;

public interface BookService {
    List<BookResponse> findBooksByStudentId(Long studentId);
}

package com.interntask.bookstore.controller;

import com.interntask.bookstore.model.response.BookResponse;
import com.interntask.bookstore.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/book")
public class BookController {
    private final BookService bookService;

    @GetMapping("/{studentId}")
    public ResponseEntity<List<BookResponse>> findBooksByStudentId(@PathVariable Long studentId) {
        return ResponseEntity.status(HttpStatus.OK).body(bookService.findBooksByStudentId(studentId));
    }
}

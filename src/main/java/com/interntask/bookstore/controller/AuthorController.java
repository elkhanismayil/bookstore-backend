package com.interntask.bookstore.controller;

import com.interntask.bookstore.model.request.CreateAuthorRequest;
import com.interntask.bookstore.model.request.CreateBookRequest;
import com.interntask.bookstore.model.response.AuthorResponse;
import com.interntask.bookstore.model.response.BookResponse;
import com.interntask.bookstore.service.AuthorService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/author")
public class AuthorController {
    private final AuthorService authorService;

    @PreAuthorize("hasRole('AUTHOR')")
    @PostMapping
    public ResponseEntity<AuthorResponse> createAuthor(@RequestBody @Valid CreateAuthorRequest createAuthorRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(authorService.createAuthor(createAuthorRequest));
    }

    @PreAuthorize("hasRole('AUTHOR')")
    @PostMapping("/{authorId}/book")
    public ResponseEntity<String> createBookForAuthor(@PathVariable Long authorId,
                                                      @RequestBody @Valid CreateBookRequest createBookRequest) {
        authorService.createBookForAuthor(authorId, createBookRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body("Book created successfully!");
    }

    @PreAuthorize("hasRole('AUTHOR')")
    @GetMapping
    public ResponseEntity<List<AuthorResponse>> getAllAuthors() {
        return ResponseEntity.status(HttpStatus.OK).body(authorService.getAllAuthorsWithBooks());
    }

    @PreAuthorize("hasRole('AUTHOR')")
    @GetMapping("/{authorId}/book")
    public ResponseEntity<List<BookResponse>> getAllBooksByAuthorId(@PathVariable Long authorId) {
        return ResponseEntity.status(HttpStatus.OK).body(authorService.getAllBooksByAuthorId(authorId));
    }

    @PreAuthorize("hasRole('AUTHOR')")
    @DeleteMapping("/{authorId}/book/{bookId}")
    public ResponseEntity<String> deleteBookByAuthorId(@PathVariable Long authorId, @PathVariable Long bookId) {
        authorService.deleteBookByAuthorId(authorId, bookId);
        return ResponseEntity.status(HttpStatus.OK).body("Book deleted successfully!");
    }
}

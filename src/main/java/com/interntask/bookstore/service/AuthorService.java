package com.interntask.bookstore.service;

import com.interntask.bookstore.model.request.CreateAuthorRequest;
import com.interntask.bookstore.model.request.CreateBookRequest;
import com.interntask.bookstore.model.response.AuthorResponse;
import com.interntask.bookstore.model.response.BookResponse;

import java.util.List;

public interface AuthorService {
    AuthorResponse createAuthor(CreateAuthorRequest createAuthorRequest);
    List<AuthorResponse> getAllAuthorsWithBooks();
    void createBookForAuthor(Long authorId, CreateBookRequest bookRequest);
    List<BookResponse> getAllBooksByAuthorId(Long authorId);
    void deleteBookByAuthorId(Long authorId, Long bookId);
}

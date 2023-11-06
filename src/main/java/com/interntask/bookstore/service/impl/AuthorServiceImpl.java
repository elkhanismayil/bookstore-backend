package com.interntask.bookstore.service.impl;

import com.interntask.bookstore.entity.Author;
import com.interntask.bookstore.entity.Book;
import com.interntask.bookstore.exception.AuthorNotFoundException;
import com.interntask.bookstore.exception.BookNotFoundException;
import com.interntask.bookstore.model.request.CreateAuthorRequest;
import com.interntask.bookstore.model.request.CreateBookRequest;
import com.interntask.bookstore.model.response.AuthorResponse;
import com.interntask.bookstore.model.response.BookResponse;
import com.interntask.bookstore.repository.AuthorRepository;
import com.interntask.bookstore.repository.BookRepository;
import com.interntask.bookstore.service.AuthorService;
import com.interntask.bookstore.producer.RabbitMQJsonProducer;
import com.interntask.bookstore.util.Converter;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Log4j2
public class AuthorServiceImpl implements AuthorService {
    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;
    private final RabbitMQJsonProducer rabbitMQJsonProducer;

    @Override
    public AuthorResponse createAuthor(CreateAuthorRequest createAuthorRequest) {
        Author author = new Author();
        author.setName(createAuthorRequest.getName());
        author.setAge(createAuthorRequest.getAge());
        author.setBooks(Converter.convertCreateBookRequestListToBookList(createAuthorRequest.getBooks()));
        authorRepository.save(author);
        log.info("=====> Author {} and books {} created successfully!", author.getName(), author.getBooks());
        return AuthorResponse.builder()
                .id(author.getId())
                .authorName(author.getName())
                .authorAge(author.getAge())
                .books(Converter.convertBookListToBookResponseList(author.getBooks()))
                .build();
    }

    public List<AuthorResponse> getAllAuthorsWithBooks() {
        return Converter.convertAuthorListToAuthorResponseList(authorRepository.findAll());
    }

    @Override
    public void createBookForAuthor(Long authorId, CreateBookRequest bookRequest) {
        Author author = authorRepository.findById(authorId).orElseThrow(() ->
                new AuthorNotFoundException("Author not found!"));
        author.getBooks().add(Converter.convertCreateBookRequestToBook(bookRequest));
        authorRepository.save(author);
        BookResponse bookResponse = BookResponse.builder()
                .bookName(bookRequest.getBookName())
                .build();
        rabbitMQJsonProducer.sendJsonMessage(bookResponse);
        log.info("=====> Book {} created successfully for author {}!", bookRequest.getBookName(), author.getName());
    }

    @Override
    public List<BookResponse> getAllBooksByAuthorId(Long authorId) {
        Author author = authorRepository.findById(authorId).orElseThrow(() ->
                new AuthorNotFoundException("Author not found!"));
        return Converter.convertBookListToBookResponseList(author.getBooks());
    }

    @Override
    public void deleteBookByAuthorId(Long authorId, Long bookId) {
        Author author = authorRepository.findById(authorId).orElseThrow(() ->
                new AuthorNotFoundException("Author not found!"));
        Book book = bookRepository.findById(bookId).orElseThrow(() ->
                new BookNotFoundException("Book not found!"));

        if (author.getBooks().contains(book)) {
            author.getBooks().remove(book);
            bookRepository.delete(book);
            log.info("=====> Book {} deleted successfully for author {}!", book.getBookName(), author.getName());
        } else {
            throw new BookNotFoundException("Book not found!");
        }

    }

}

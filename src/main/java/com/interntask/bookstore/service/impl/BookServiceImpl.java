package com.interntask.bookstore.service.impl;

import com.interntask.bookstore.entity.Book;
import com.interntask.bookstore.model.response.BookResponse;
import com.interntask.bookstore.repository.BookRepository;
import com.interntask.bookstore.service.BookService;
import com.interntask.bookstore.util.Converter;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Log4j2
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;
    @Override
    public List<BookResponse> findBooksByStudentId(Long studentId) {
        log.info("====> Finding books by student id: {}", studentId);
        List<Book> books = bookRepository.findBooksByStudentId(studentId);
        return Converter.convertBookListToBookResponseList(books);
    }
}

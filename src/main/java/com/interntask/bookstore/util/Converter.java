package com.interntask.bookstore.util;

import com.interntask.bookstore.entity.Author;
import com.interntask.bookstore.entity.Book;
import com.interntask.bookstore.entity.Student;
import com.interntask.bookstore.model.request.CreateBookRequest;
import com.interntask.bookstore.model.response.AuthorResponse;
import com.interntask.bookstore.model.response.BookResponse;
import com.interntask.bookstore.model.response.StudentResponse;

import java.util.ArrayList;
import java.util.List;

public class Converter {
    public static BookResponse convertBookToBookResponse(Book book) {
        BookResponse bookResponse = new BookResponse();
        bookResponse.setId(book.getId());
        bookResponse.setBookName(book.getBookName());
        return bookResponse;
    }

    public static List<BookResponse> convertBookListToBookResponseList(List<Book> bookList) {
        List<BookResponse> bookResponseList = new ArrayList<>();
        for (Book book : bookList) {
            bookResponseList.add(convertBookToBookResponse(book));
        }
        return bookResponseList;
    }

    public static StudentResponse convertStudentToStudentResponse(Student student) {
        StudentResponse studentResponse = new StudentResponse();
        studentResponse.setId(student.getId());
        studentResponse.setName(student.getName());
        studentResponse.setAge(student.getAge());
        studentResponse.setBooks(convertBookListToBookResponseList(student.getBooks()));
        return studentResponse;
    }

    public static List<StudentResponse> convertStudentListToStudentResponseList(List<Student> studentList) {
        List<StudentResponse> studentResponseList = new ArrayList<>();
        for (Student student : studentList) {
            studentResponseList.add(convertStudentToStudentResponse(student));
        }
        return studentResponseList;
    }

    public static List<Book> convertCreateBookRequestListToBookList(List<CreateBookRequest> books) {
        List<Book> bookList = new ArrayList<>();
        for (CreateBookRequest book : books) {
            Book book1 = new Book();
            book1.setBookName(book.getBookName());
            bookList.add(book1);
        }
        return bookList;
    }

    public static List<AuthorResponse> convertAuthorListToAuthorResponseList(List<Author> authors) {
        List<AuthorResponse> authorResponseList = new ArrayList<>();
        for (Author author : authors) {
            AuthorResponse authorResponse = new AuthorResponse();
            authorResponse.setId(author.getId());
            authorResponse.setAuthorName(author.getName());
            authorResponse.setAuthorAge(author.getAge());
            List<BookResponse> bookResponses = new ArrayList<>();
            List<Book> books = author.getBooks();
            for (Book book : books) {
                bookResponses.add(convertBookToBookResponse(book));
            }
            authorResponse.setBooks(convertBookListToBookResponseList(books));
            authorResponseList.add(authorResponse);
        }
        return authorResponseList;
    }

    public static Book convertCreateBookRequestToBook(CreateBookRequest bookRequest) {
        Book book = new Book();
        book.setBookName(bookRequest.getBookName());
        return book;
    }
}

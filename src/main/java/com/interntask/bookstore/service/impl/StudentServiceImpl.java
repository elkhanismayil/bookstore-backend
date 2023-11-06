package com.interntask.bookstore.service.impl;

import com.interntask.bookstore.entity.Book;
import com.interntask.bookstore.entity.Student;
import com.interntask.bookstore.exception.BookNotFoundException;
import com.interntask.bookstore.exception.StudentNotFoundException;
import com.interntask.bookstore.model.request.CreateStudentRequest;
import com.interntask.bookstore.model.response.StudentResponse;
import com.interntask.bookstore.repository.BookRepository;
import com.interntask.bookstore.repository.StudentRepository;
import com.interntask.bookstore.service.StudentService;
import com.interntask.bookstore.util.Converter;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Log4j2
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;
    private final BookRepository bookRepository;

    @Transactional
    @Override
    public StudentResponse createStudent(CreateStudentRequest createStudentRequest) {
        Student student = Student.builder()
                .name(createStudentRequest.getName())
                .age(createStudentRequest.getAge())
                .build();
        studentRepository.save(student);

        return StudentResponse.builder()
                .id(student.getId())
                .name(student.getName())
                .age(student.getAge())
                .build();

    }

    @Override
    public StudentResponse addBookToStudent(Long studentId, Long bookId) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new StudentNotFoundException("Student not found"));

        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new BookNotFoundException("Book not found"));

        if (student.getBooks().contains(book)) {
            throw new BookNotFoundException("Book already exists in this student");
        }

        student.getBooks().add(book);
        studentRepository.save(student);
        return Converter.convertStudentToStudentResponse(student);
    }

    @Override
    public List<StudentResponse> getStudentsByBookId(Long bookId) {
        List<Student> allByBooksId = studentRepository.findAllByBooks_Id(bookId);
        return Converter.convertStudentListToStudentResponseList(allByBooksId);
    }

}

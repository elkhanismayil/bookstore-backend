package com.interntask.bookstore.service;

import com.interntask.bookstore.model.request.CreateStudentRequest;
import com.interntask.bookstore.model.response.StudentResponse;

import java.util.List;

public interface StudentService {
    StudentResponse createStudent(CreateStudentRequest createStudentRequest);
    StudentResponse addBookToStudent(Long studentId, Long bookId);
    List<StudentResponse> getStudentsByBookId(Long bookId);
}

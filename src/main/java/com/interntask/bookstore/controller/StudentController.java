package com.interntask.bookstore.controller;

import com.interntask.bookstore.model.request.CreateStudentRequest;
import com.interntask.bookstore.model.response.StudentResponse;
import com.interntask.bookstore.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/student")
@RequiredArgsConstructor
public class StudentController {

    private final StudentService studentService;

    @PreAuthorize("hasRole('STUDENT')")
    @PostMapping
    public ResponseEntity<StudentResponse> createStudent(@RequestBody CreateStudentRequest createStudentRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(studentService.createStudent(createStudentRequest));
    }

    @PreAuthorize("hasRole('STUDENT')")
    @PostMapping("/{studentId}/book/{bookId}")
    public ResponseEntity<StudentResponse> addBookToStudent(@PathVariable Long studentId, @PathVariable Long bookId) {
        return ResponseEntity.status(HttpStatus.OK).body(studentService.addBookToStudent(studentId, bookId));
    }

    @PreAuthorize("hasRole('STUDENT')")
    @GetMapping("/{bookId}")
    public ResponseEntity<List<StudentResponse>> getStudentsByBookId(@PathVariable Long bookId) {
        return ResponseEntity.status(HttpStatus.OK).body(studentService.getStudentsByBookId(bookId));
    }
}

package com.interntask.bookstore.repository;

import com.interntask.bookstore.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Long> {
    List<Student> findAllByBooks_Id(Long bookId);
}
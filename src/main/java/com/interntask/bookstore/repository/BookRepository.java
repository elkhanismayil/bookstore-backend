package com.interntask.bookstore.repository;

import com.interntask.bookstore.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long> {
    @Query("SELECT b FROM Book b JOIN b.students s WHERE s.id = ?1")
    List<Book> findBooksByStudentId(Long studentId);
}
package com.example.academicportal.repository;

import com.example.academicportal.entity.Student;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
    // LEFT JOIN FETCH - Fetch all students even if they have no enrollments
    @Query("SELECT s FROM Student s LEFT JOIN FETCH s.enrollments")  
    List<Student> findAllStudentsWithEnrollments();  
    // LEFT JOIN will return all students with or without enrollments
}

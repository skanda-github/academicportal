package com.example.academicportal.repository;

import com.example.academicportal.entity.Enrollment;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface EnrollmentRepository extends JpaRepository<Enrollment, Long> {
     // INNER JOIN - Fetch only enrollments matching a student ID
    @Query("SELECT e FROM Enrollment e JOIN e.student s WHERE s.id = :studentId")
    List<Enrollment> findByStudentId(Long studentId);  // INNER JOIN: Only returns enrollments with a matching student ID
}

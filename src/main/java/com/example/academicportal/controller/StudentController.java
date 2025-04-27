package com.example.academicportal.controller;

import com.example.academicportal.entity.Enrollment;
import com.example.academicportal.entity.Student;
import com.example.academicportal.service.StudentService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    // ============================
    // Save a new student
    // ============================
    @PostMapping
    public Student saveStudent(@RequestBody Student student) {
        return studentService.saveStudent(student);  // Calls the service layer to save the student
    }

    // ============================
    // Enroll a student into a course (Transaction in service)
    // ============================
    @PostMapping("/{studentId}/enroll/{courseId}")
    public Enrollment enrollStudent(@PathVariable Long studentId, @PathVariable Long courseId) {
        return studentService.enrollStudent(studentId, courseId);  // Calls service layer to enroll
    }

    // ============================
    // Get all students with their enrollments (LEFT JOIN)
    // ============================
    @GetMapping("/with-enrollments")
    public List<Student> getAllStudentsWithEnrollments() {
        return studentService.findAllStudentsWithEnrollments();  // Calls service to get all students with enrollments
    }

    // ============================
    // Get all enrollments of a particular student (INNER JOIN)
    // ============================
    @GetMapping("/{studentId}/enrollments")
    public List<Enrollment> getEnrollmentsByStudent(@PathVariable Long studentId) {
        return studentService.findEnrollmentsByStudentId(studentId);  // Calls service to get enrollments for a student
    }
}
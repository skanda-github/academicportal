package com.example.academicportal.service;

import com.example.academicportal.entity.Course;
import com.example.academicportal.entity.Enrollment;
import com.example.academicportal.entity.Student;
import com.example.academicportal.repository.CourseRepository;
import com.example.academicportal.repository.EnrollmentRepository;
import com.example.academicportal.repository.StudentRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;

@Service
public class StudentService {

    private final StudentRepository studentRepository;
    private final CourseRepository courseRepository;
    private final EnrollmentRepository enrollmentRepository;

    public StudentService(StudentRepository studentRepository, CourseRepository courseRepository,
                           EnrollmentRepository enrollmentRepository) {
        this.studentRepository = studentRepository;
        this.courseRepository = courseRepository;
        this.enrollmentRepository = enrollmentRepository;
    }

    // ============================
    // Saving Student - ACID Properties
    // Atomic operation: either all saved or nothing saved.
    // ============================
    @Transactional  // ensures atomicity
    public Student saveStudent(Student student) {
        return studentRepository.save(student);  // ACID: If save fails, everything is rolled back
    }

    // ============================
    // Enrolling student into a course - Transaction
    // ACID: Ensures enrollment is saved only if both Student and Course exist
    // ============================
    @Transactional  // Transactional ensures consistency and rollback in case of failure
    public Enrollment enrollStudent(Long studentId, Long courseId) {
        // Fetch the student (if not found, throw exception)
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new RuntimeException("Student not found"));

        // Fetch the course (if not found, throw exception)
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new RuntimeException("Course not found"));

        // Creating and saving enrollment - atomic transaction
        Enrollment enrollment = new Enrollment();
        enrollment.setStudent(student);
        enrollment.setCourse(course);
        enrollment.setEnrolledDate(LocalDate.now());

        return enrollmentRepository.save(enrollment);  // If save fails, entire transaction is rolled back
    }

    // ============================
    // Find all students and fetch their enrollments (LEFT OUTER JOIN)
    // Fetches students even if they have no enrollments
    // ============================
    public List<Student> findAllStudentsWithEnrollments() {
        return studentRepository.findAllStudentsWithEnrollments();  // Uses LEFT JOIN
    }

    // ============================
    // Get all enrollments of a specific student (INNER JOIN)
    // ============================
    public List<Enrollment> findEnrollmentsByStudentId(Long studentId) {
        return enrollmentRepository.findByStudentId(studentId);  // Uses INNER JOIN
    }
}
package com.example.academicportal.data;

import com.example.academicportal.entity.Course;
import com.example.academicportal.entity.Enrollment;
import com.example.academicportal.entity.Instructor;
import com.example.academicportal.entity.Student;
import com.example.academicportal.repository.CourseRepository;
import com.example.academicportal.repository.EnrollmentRepository;
import com.example.academicportal.repository.InstructorRepository;
import com.example.academicportal.repository.StudentRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

// The CommandLineRunner runs when the Spring Boot application starts. 
// We’ll use it to insert sample data into the database automatically.

@Component
public class DataInitializer implements CommandLineRunner {

    private final StudentRepository studentRepository;
    private final CourseRepository courseRepository;
    private final InstructorRepository instructorRepository;
    private final EnrollmentRepository enrollmentRepository;

    public DataInitializer(StudentRepository studentRepository, CourseRepository courseRepository, 
                           InstructorRepository instructorRepository, EnrollmentRepository enrollmentRepository) {
        this.studentRepository = studentRepository;
        this.courseRepository = courseRepository;
        this.instructorRepository = instructorRepository;
        this.enrollmentRepository = enrollmentRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        // Create Instructors
        Instructor instructor1 = new Instructor();
        instructor1.setFirstName("John");
        instructor1.setLastName("Doe");
        instructorRepository.save(instructor1);

        Instructor instructor2 = new Instructor();
        instructor2.setFirstName("Jane");
        instructor2.setLastName("Smith");
        instructorRepository.save(instructor2);

        // Create Courses
        Course course1 = new Course();
        course1.setName("Math 101");
        course1.setDescription("Basic Mathematics");
        course1.setInstructor(instructor1);
        courseRepository.save(course1);

        Course course2 = new Course();
        course2.setName("Science 101");
        course2.setDescription("Basic Science");
        course2.setInstructor(instructor2);
        courseRepository.save(course2);

        // Create Students
        Student student1 = new Student();
        student1.setFirstName("Alice");
        student1.setLastName("Williams");
        studentRepository.save(student1);

        Student student2 = new Student();
        student2.setFirstName("Bob");
        student2.setLastName("Johnson");
        studentRepository.save(student2);

        // Enroll Students to Courses
        Enrollment enrollment1 = new Enrollment();
        enrollment1.setStudent(student1);
        enrollment1.setCourse(course1);
        enrollment1.setEnrolledDate(LocalDate.now());
        enrollmentRepository.save(enrollment1);

        Enrollment enrollment2 = new Enrollment();
        enrollment2.setStudent(student2);
        enrollment2.setCourse(course2);
        enrollment2.setEnrolledDate(LocalDate.now());
        enrollmentRepository.save(enrollment2);

        System.out.println("Sample data initialized!");
    }
}
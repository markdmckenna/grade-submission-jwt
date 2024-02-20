package com.ltp.gradesubmission.web;

import java.util.List;
import java.util.Set;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ltp.gradesubmission.entity.Course;
import com.ltp.gradesubmission.entity.Student;
import com.ltp.gradesubmission.service.StudentService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/student")
public class StudentController {

    private static final Logger LOGGER = LoggerFactory.getLogger(StudentController.class);

    private StudentService studentService;

    @GetMapping("/{id}")
    public ResponseEntity<Student> getStudent(@PathVariable Long id) {
        LOGGER.info("[IN]StudentController - getStudent - id: {}", id);
        Student student = studentService.getStudent(id);
        LOGGER.info("[OUT]StudentController - getStudent - student: {}", student);
        return new ResponseEntity<>(student, HttpStatus.OK);
    }

    @PostMapping("/")
    public ResponseEntity<Student> saveStudent(@Valid @RequestBody Student student) {
        LOGGER.info("[IN]StudentController - saveStudent - student: {}", student);
        student = studentService.saveStudent(student);
        LOGGER.info("[OUT]StudentController - saveStudent - student: {}", student);
        return new ResponseEntity<>(student, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Student> deleteStudent(@PathVariable Long id) {
        LOGGER.info("[IN]StudentController - deleteStudent - id: {}", id);
        studentService.deleteStudent(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Student>> getStudents() {
        LOGGER.info("[IN]StudentController - getStudents");
        List<Student> students = studentService.getStudents();
        LOGGER.info("[OUT]StudentController - getStudents - students: {}", students);
        return new ResponseEntity<>(students, HttpStatus.OK);
    }

    @GetMapping("/{id}/courses")
    public ResponseEntity<Set<Course>> getEnrolledCourses(@PathVariable Long id) {
        return new ResponseEntity<>(studentService.getEnrolledCourses(id), HttpStatus.OK);
    }

}

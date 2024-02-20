package com.ltp.gradesubmission.web;


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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ltp.gradesubmission.entity.Course;
import com.ltp.gradesubmission.entity.Student;
import com.ltp.gradesubmission.service.CourseService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/course")
public class CourseController {

    private static final Logger LOGGER = LoggerFactory.getLogger(CourseController.class);

    private CourseService courseService;

    @GetMapping("/{id}")
    public ResponseEntity<Course> getCourse(@PathVariable Long id) {
        LOGGER.info("[IN]CourseController - getCourse - id: {}", id);
        Course course = this.courseService.getCourse(id);
        LOGGER.info("[OUT]CourseController - getCourse - course: {}", course);
        return new ResponseEntity<>(course, HttpStatus.OK);
    }

    @PostMapping("/")
    public ResponseEntity<Course> saveCourse(@Valid @RequestBody Course course) {
        LOGGER.info("[IN]CourseController - saveCourse - course: {}", course);
        course = courseService.saveCourse(course);
        LOGGER.info("[OUT]CourseController - saveCourse - course: {}", course);
        return new ResponseEntity<>(course, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteCourse(@PathVariable Long id) {
        LOGGER.info("[IN]CourseController - deleteCourse - id: {}", id);
        this.courseService.deleteCourse(id);
        LOGGER.info("[OUT]CourseController - deleteCourse");
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/all")
    public ResponseEntity<Set<Course>> getCourses() {
        LOGGER.info("[IN]CourseController - getCourses");
        Set<Course> courses = this.courseService.getCourses();
        LOGGER.info("[OUT]CourseController - getCourses - courses: {}", courses);
        return new ResponseEntity<>(courses, HttpStatus.OK);
    }

    @PutMapping("/{courseId}/student/{studentId}")
    public ResponseEntity<Course> enrollStudentToCourse(@PathVariable Long courseId, @PathVariable Long studentId) {
        return new ResponseEntity<>(courseService.addStudentToCourse(studentId, courseId), HttpStatus.OK);
    }

    @GetMapping("/{id}/students")
    public ResponseEntity<Set<Student>> getEnrolledStudents(@PathVariable Long id) {
        return new ResponseEntity<>(courseService.getEnrolledStudents(id), HttpStatus.OK);
    }


}
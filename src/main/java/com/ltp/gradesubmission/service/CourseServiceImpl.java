package com.ltp.gradesubmission.service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.ltp.gradesubmission.entity.Course;
import com.ltp.gradesubmission.entity.Student;
import com.ltp.gradesubmission.exception.CourseNotFoundException;
import com.ltp.gradesubmission.repository.CourseRepository;
import com.ltp.gradesubmission.repository.StudentRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class CourseServiceImpl implements CourseService {

    private CourseRepository courseRepository;

    private StudentRepository studentRepository;

    @Override
    public Course getCourse(Long id) {
        Optional<Course> course = courseRepository.findById(id);
        return unwrapCourse(course, id);
    }

    @Override
    public Course saveCourse(Course course) {
        return courseRepository.save(course);
    }

    @Override
    public void deleteCourse(Long id) {
        courseRepository.deleteById(id);
    }

    @Override
    public Set<Course> getCourses() {
        List<Course> courseList =  (List<Course>) courseRepository.findAll();
        return new HashSet<>(courseList);
    }

    @Override
    public Course addStudentToCourse(Long studentId, Long courseId) {
        Course course = this.getCourse(courseId);
        Optional<Student> student = studentRepository.findById(studentId);
        Student unwrappedStudent = StudentServiceImpl.unwrapStudent(student, studentId);
        course.getStudents().add(unwrappedStudent);
        return courseRepository.save(course);
    }

    @Override
    public Set<Student> getEnrolledStudents(Long id) {
        Course course = getCourse(id);
        return course.getStudents();
    }

    static Course unwrapCourse(Optional<Course> entity, Long id) {
        if (entity.isPresent())
            return entity.get();
        else
            throw new CourseNotFoundException(id);
    }

}

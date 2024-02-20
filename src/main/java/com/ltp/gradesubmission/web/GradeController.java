package com.ltp.gradesubmission.web;

import java.util.List;

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

import com.ltp.gradesubmission.entity.Grade;
import com.ltp.gradesubmission.service.GradeService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/grade")
public class GradeController {

    private GradeService gradeService;

    private static final Logger LOGGER = LoggerFactory.getLogger(GradeController.class);

    @GetMapping("/student/{studentId}/course/{courseId}")
    public ResponseEntity<Grade> getGrade(@PathVariable Long studentId, @PathVariable Long courseId) {
        LOGGER.info("[IN]GradeController - getGrade - studentId: {} - courseId: {}", studentId, courseId);
        Grade grade = gradeService.getGrade(studentId, courseId);
        LOGGER.info("[OUT]GradeController - getGrade - grade: {}", grade);
        return new ResponseEntity<>(grade, HttpStatus.OK);
    }

    @PostMapping("/student/{studentId}/course/{courseId}")
    public ResponseEntity<Grade> saveGrade(@RequestBody Grade grade, @PathVariable Long studentId,
            @PathVariable Long courseId) {
        Object[] logArgs = { grade, studentId, courseId };
        LOGGER.info("[IN]GradeController - saveGrade - grade: {} - studentId: {} - courseId: {}", logArgs);
        grade = gradeService.saveGrade(grade, studentId, courseId);
        LOGGER.info("[OUT]GradeController - saveGrade - grade: {}", grade);
        return new ResponseEntity<>(grade, HttpStatus.CREATED);
    }

    @PutMapping("/student/{studentId}/course/{courseId}")
    public ResponseEntity<Grade> updateGrade(@RequestBody Grade grade, @PathVariable Long studentId,
            @PathVariable Long courseId) {
        Object[] logArgs = { grade, studentId, courseId };
        LOGGER.info("[IN]GradeController - updateGrade - grade: {} - studentId: {} - courseId: {}", logArgs);
        grade = gradeService.updateGrade(grade.getScore(), studentId, courseId);
        return new ResponseEntity<>(grade, HttpStatus.OK);
    }

    @DeleteMapping("/student/{studentId}/course/{courseId}")
    public ResponseEntity<Grade> deleteGrade(@PathVariable Long studentId,
            @PathVariable Long courseId) {
        Object[] logArgs = { studentId, courseId };
        LOGGER.info("[IN]GradeController - deleteGrade - studentId: {} - courseId: {}", logArgs);
        this.gradeService.deleteGrade(studentId, courseId);
        LOGGER.info("[OUT]GradeController - deleteGrade");
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/student/{studentId}")
    public ResponseEntity<List<Grade>> getStudentGrades(@PathVariable Long studentId) {
        LOGGER.info("[IN]GradeController - getStudentGrades - studentId: {}", studentId);
        List<Grade> grades = this.gradeService.getStudentGrades(studentId);
        LOGGER.info("[OUT]GradeController - getStudentGrades - grades: {}", grades);
        return new ResponseEntity<>(grades, HttpStatus.OK);
    }

    @GetMapping("/course/{courseId}")
    public ResponseEntity<List<Grade>> getCourseGrades(@PathVariable Long courseId) {
        LOGGER.info("[IN]GradeController - getCourseGrades - courseId: {}", courseId);
        List<Grade> grades = this.gradeService.getCourseGrades(courseId);
        LOGGER.info("[OUT]GradeController - getCourseGrades - grades: {}", grades);
        return new ResponseEntity<>(grades, HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Grade>> getGrades() {
        LOGGER.info("[IN]GradeController - getGrades");
        List<Grade> grades = this.gradeService.getAllGrades();
        return new ResponseEntity<>(grades, HttpStatus.OK);
    }

}

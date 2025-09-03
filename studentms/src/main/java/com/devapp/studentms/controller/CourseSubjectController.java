package com.devapp.studentms.controller;

import com.devapp.studentms.request.CourseSubjectRequest;
import com.devapp.studentms.response.CourseSubjectResponse;
import com.devapp.studentms.service.CourseSubjectService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/course-subject")
@Tag(name = "CourseSubject APIs")
public class CourseSubjectController {
    private final CourseSubjectService courseSubjectService;

    // Get all courseSubject
    // GET url: http://localhost:8080/api/course-subject
    @GetMapping
    public ResponseEntity<?> getAllCourseSubject() {
        List<CourseSubjectResponse> responses = courseSubjectService.getAllCourseSubjects();
        if(responses.isEmpty()) return ResponseEntity.ok("Empty, that means no record found in DB");
        return ResponseEntity.ok(responses);
    }

    // Create CourseSubject
    // POST url: http://localhost:8080/api/course-subject?courseId=1&subjectId=2
    @PostMapping(params = {"courseId", "subjectId"})
    public ResponseEntity<?> createCourseSubject(
            @RequestParam("courseId") Long courseId,
            @RequestParam("subjectId") Long subjectId,
            @RequestBody CourseSubjectRequest request) {
        try {
            CourseSubjectResponse response = courseSubjectService.addCourseSubject(courseId, subjectId, request);
            if (response == null) return ResponseEntity.status(404).body("CourseSubject not found with courseId: " + courseId + " and subjectId: " + subjectId);

            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        }
        catch (IllegalArgumentException ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }

    // Get CourseSubject by courseId and subjectId
    // GET url: http://localhost:8080/api/course-subject?courseId=3&subjectId=3
    @GetMapping(params = {"courseId", "subjectId"})
    public ResponseEntity<?> fetchCourseSubject(
            @RequestParam("courseId") Long courseId,
            @RequestParam("subjectId") Long subjectId) {
        CourseSubjectResponse response = courseSubjectService.getCourseSubject(courseId, subjectId);
        if(response != null) return ResponseEntity.ok(response);

        return ResponseEntity.status(404).body("CourseSubject not found with courseId: " + courseId + " and subjectId: " + subjectId);
    }

    // Update CourseSubject by courseId and subjectId
    // PUT url: http://localhost:8080/api/course-subject?courseId=1&subjectId=2
    @PutMapping(params = {"courseId", "subjectId"})
    public ResponseEntity<?> updateCourseSubject(
            @RequestParam("courseId") Long courseId,
            @RequestParam("subjectId") Long subjectId,
            @RequestBody CourseSubjectRequest updatedCourseSubject) {
        try {
            CourseSubjectResponse response = courseSubjectService.updateCourseSubject(courseId, subjectId, updatedCourseSubject);
            if (response == null) return ResponseEntity.status(404).body("CourseSubject not found with courseId: " + courseId + " & subjectId: " + subjectId);

            return ResponseEntity.ok(response);
        }
        catch (IllegalArgumentException ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }

    // Delete CourseSubject by courseId and subjectId
    // DELETE url: http://localhost:8080/api/course-subject?courseId=2&subjectId=1
    @DeleteMapping(params = {"courseId", "subjectId"})
    public ResponseEntity<?> deleteCourseSubject(
            @RequestParam Long courseId,
            @RequestParam Long subjectId) {
        boolean deleted = courseSubjectService.deleteCourseSubject(courseId, subjectId);
        if(deleted) return ResponseEntity.status(HttpStatus.OK).body("CourseSubject deleted successfully of courseId: " + courseId + " & subjectId: " + subjectId);

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("CourseSubject not found with courseId: " + courseId + " & subjectId: " + subjectId);
    }
}
package com.devapp.studentms.controller;

import com.devapp.studentms.request.CourseRequest;
import com.devapp.studentms.response.CourseResponse;
import com.devapp.studentms.response.StudentResponse;
import com.devapp.studentms.response.SubjectResponse;
import com.devapp.studentms.service.CourseService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/courses")
@Tag(name = "Course APIs")
public class CourseController {
    private final CourseService courseService;

    // Fetch all Courses
    // GET url: http://localhost:8080/api/courses
    @GetMapping
    public ResponseEntity<?> getCourses() {
        List<CourseResponse> responses = courseService.fetchCourses();
        if(responses.isEmpty()) return ResponseEntity.ok("No Course record found.");

        return ResponseEntity.ok(responses);
    }

    // Create a Course
    // POST url: http://localhost:8080/api/courses
    @PostMapping
    public ResponseEntity<?> createCourse(@RequestBody CourseRequest request) {
        try {
            CourseResponse response = courseService.addCourse(request);
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        }
        catch (IllegalArgumentException ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }

    // Fetch Course by courseId
    // GET url: http://localhost:8080/api/courses/2
    @GetMapping("/{id}")
    public ResponseEntity<?> getCourse(@PathVariable("id") Long courseId) {
        CourseResponse response = courseService.fetchCourse(courseId);
        if (response == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No record available in the DB of courseId: " + courseId);
        }
        return ResponseEntity.ok(response);
    }

    // Delete Course by courseId
    // DELETE url: http://localhost:8080/api/courses/1
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCourse(@PathVariable("id") Long courseId) {
        boolean deleted = courseService.deleteCourse(courseId);
        if(deleted) return ResponseEntity.status(HttpStatus.OK).body("Course record is deleted successfully, of courseId: " + courseId);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No record available in the DB of courseId: " + courseId);
    }

    // Update Course by courseId and updatedCourse
    // PUT url: http://localhost:8080/api/courses/1
    @PutMapping("/{id}")
    public ResponseEntity<?> updateCourse(
            @PathVariable("id") Long courseId,
            @RequestBody CourseRequest updatedRequest) {
        try {
            CourseResponse response = courseService.updateCourse(courseId, updatedRequest);

            if (response == null) return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No course record found in DB of courseId: " + courseId);

            return ResponseEntity.ok(response);
        }
        catch (IllegalArgumentException ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }

    //--------------------------------------------------------------------------------
    //--------------------------ADVANCE API End Points--------------------------------

    // Fetch all Students of a particular Course
    // GET url: http://localhost:8080/api/courses/{id}/students
    @GetMapping("/{id}/students")
    @Operation(summary = "Fetch all students of a course")
    public ResponseEntity<?> fetchAllStudentsOfCourse(@PathVariable("id") Long courseId) {
        List<StudentResponse> responses = courseService.findAllStudentsByCourse(courseId);

        if(responses == null) return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Invalid courseId: " + courseId);

        if (responses.isEmpty()) {
            return ResponseEntity.status(HttpStatus.OK)
                    .body("No Student record found for COURSE_ID: " + courseId);
        }

        return ResponseEntity.status(HttpStatus.OK).body(responses);
    }

    // Fetch all ACTIVE Students of a particular Course
    // GET url: http://localhost:8080/api/courses/{id}/students/status?status=Active
    @GetMapping("/{id}/students/status")
    @Operation(summary = "Fetch all ACTIVE or INACTIVE students of a course")
    public ResponseEntity<?> fetchAllStudentsBasedOnStatus(
            @PathVariable("id") Long courseId,
            @RequestParam("status") String status) {
        try {
            List<StudentResponse> responses = courseService.findAllStudentsBasedOnStatus(courseId, status);
            if (responses == null)
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Invalid courseId: " + courseId);

            if (responses.isEmpty()) {
                return ResponseEntity.status(HttpStatus.OK)
                        .body("No Student found with status " + status.toUpperCase() + " for COURSE_ID: " + courseId);
            }

            return ResponseEntity.status(HttpStatus.OK).body(responses);
        } catch (IllegalArgumentException ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }

    // Fetch all Subjects of a particular Course
    // GET url: http://localhost:8080/api/courses/{id}/subjects
    @GetMapping("/{id}/subjects")
    public ResponseEntity<?> fetchAllSubjectsOfCourse(@PathVariable("id") Long courseId) {
        List<SubjectResponse> responses = courseService.findAllSubjectsByCourse(courseId);
        if(responses == null) return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No course record found in DB of courseId: " + courseId);

        if (responses.isEmpty()) {
            return ResponseEntity.status(HttpStatus.OK)
                    .body("No Subject record found for COURSE_ID: " + courseId);
        }

        return ResponseEntity.status(HttpStatus.OK).body(responses);
    }
}
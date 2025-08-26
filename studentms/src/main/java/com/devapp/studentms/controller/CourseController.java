package com.devapp.studentms.controller;

import com.devapp.studentms.request.CourseRequest;
import com.devapp.studentms.response.CourseResponse;
import com.devapp.studentms.service.CourseService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/courses")
public class CourseController {
    private final CourseService courseService;

    // Fetch all Courses
    // GET url: http://localhost:8080/api/courses
    @GetMapping
    public ResponseEntity<List<CourseResponse>> getCourses() {
        List<CourseResponse> responses = courseService.fetchCourses();
        return ResponseEntity.ok(responses);
    }

    // Create a Course
    // POST url: http://localhost:8080/api/courses
    @PostMapping
    public ResponseEntity<CourseResponse> createCourse(@RequestBody CourseRequest request) {
        CourseResponse response = courseService.addCourse(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    // Fetch Course by courseId
    // GET url: http://localhost:8080/api/courses/2
    @GetMapping("/{id}")
    public ResponseEntity<CourseResponse> getCourse(@PathVariable("id") Long courseId) {
        CourseResponse response = courseService.fetchCourse(courseId);
        if (response == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(response);
    }

    // Delete Course by courseId
    // DELETE url: http://localhost:8080/api/courses/1
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCourse(@PathVariable("id") Long courseId) {
        boolean deleted = courseService.deleteCourse(courseId);
        if(deleted) return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    // Update Course by courseId and updatedCourse
    // PUT url: http://localhost:8080/api/courses/1
    @PutMapping("/{id}")
    public ResponseEntity<CourseResponse> updateCourse(
            @PathVariable("id") Long courseId,
            @RequestBody CourseRequest updatedRequest) {
        CourseResponse response = courseService.updateCourse(courseId, updatedRequest);

        if(response == null) return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

        return ResponseEntity.ok(response);
    }
}

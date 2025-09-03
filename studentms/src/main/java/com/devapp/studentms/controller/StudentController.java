package com.devapp.studentms.controller;

import com.devapp.studentms.request.StudentRequest;
import com.devapp.studentms.response.StudentResponse;
import com.devapp.studentms.service.StudentService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/students")
@Tag(name = "Student APIs")
public class StudentController {
    private final StudentService studentService;

    // Get all students
    // GET url: http://localhost:8080/api/students
    @GetMapping
    public ResponseEntity<?> fetchStudents() {
        List<StudentResponse> response = studentService.getStudents();
        if(response.isEmpty()) return ResponseEntity.ok("No Student record found in DB");
        return ResponseEntity.ok(response);
    }

    // Create Student
    // POST url: http://localhost:8080/api/students
    @PostMapping
    public ResponseEntity<?> createStudent(@RequestBody StudentRequest request) {
        try {
            StudentResponse response = studentService.addStudent(request);
            if(response == null) return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No Course record found in DB of courseId: " + request.getCourseId());

            return new ResponseEntity<>(response, HttpStatus.CREATED);
        }
        catch (IllegalArgumentException ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }

    // Get Student by studentId
    // GET url: http://localhost:8080/api/students/3
    @GetMapping("/{id}")
    public ResponseEntity<?> getStudent(@PathVariable("id") Long studentId) {
        StudentResponse response = studentService.getStudent(studentId);
        if(response == null) return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No Student record found in DB, of studentId: " + studentId);

        return ResponseEntity.ok(response);
    }

    // Update Student by studentId and updatedStudent details
    // PUT url: http://localhost:8080/api/students/2
    @PutMapping("/{id}")
    public ResponseEntity<?> updateStudent(
            @PathVariable("id") Long studentId,
            @RequestBody StudentRequest updatedStudent) {
        try {
            StudentResponse response = studentService.updateStudent(studentId, updatedStudent);

            if (response == null)
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No student record found in DB of studentId: " + studentId);

            return ResponseEntity.ok(response);
        }
        catch (IllegalArgumentException ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }

    // Delete Student by studentId
    // DELETE url: http://localhost:8080/api/students/5
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteStudent(@PathVariable("id") Long studentId) {
        boolean deleted = studentService.deleteStudent(studentId);
        if(deleted) return ResponseEntity.status(HttpStatus.OK).body("Student record is successfully deleted, studentId: " + studentId);

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Invalid studentId: " + studentId);
    }
}
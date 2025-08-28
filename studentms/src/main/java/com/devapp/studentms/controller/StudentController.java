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
    public ResponseEntity<List<StudentResponse>> fetchStudents() {
        List<StudentResponse> response = studentService.getStudents();
        return ResponseEntity.ok(response);
    }

    // Create Student
    // POST url: http://localhost:8080/api/students
    @PostMapping
    public ResponseEntity<StudentResponse> createStudent(@RequestBody StudentRequest request) {
        StudentResponse response = studentService.addStudent(request);
        if(response == null) return ResponseEntity.notFound().build();

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    // Get Student by studentId
    // GET url: http://localhost:8080/api/students/3
    @GetMapping("/{id}")
    public ResponseEntity<StudentResponse> getStudent(@PathVariable("id") Long studentId) {
        StudentResponse response = studentService.getStudent(studentId);
        if(response == null) return ResponseEntity.notFound().build();

        return ResponseEntity.ok(response);
    }

    // Update Student by studentId and updatedStudent details
    // PUT url: http://localhost:8080/api/students/2
    @PutMapping("/{id}")
    public ResponseEntity<StudentResponse> updateStudent(
            @PathVariable("id") Long studentId,
            @RequestBody StudentRequest updatedStudent) {
        StudentResponse response = studentService.updateStudent(studentId, updatedStudent);

        if(response == null) return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

        return ResponseEntity.ok(response);
    }

    // Delete Student by studentId
    // DELETE url: http://localhost:8080/api/students/5
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteStudent(@PathVariable("id") Long studentId) {
        boolean deleted = studentService.deleteStudent(studentId);
        if(deleted) return ResponseEntity.status(HttpStatus.NO_CONTENT).build();

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Invalid student ID");
    }
}
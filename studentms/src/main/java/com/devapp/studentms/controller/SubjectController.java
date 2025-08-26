package com.devapp.studentms.controller;

import com.devapp.studentms.request.SubjectRequest;
import com.devapp.studentms.response.SubjectResponse;
import com.devapp.studentms.service.SubjectService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/subjects")
public class SubjectController {
    private final SubjectService subjectService;

    // Get all subjects
    // GET url: http://localhost:8080/api/subjects
    @GetMapping
    public ResponseEntity<List<SubjectResponse>> fetchAllSubjects() {
        List<SubjectResponse> responses = subjectService.getAllSubjects();
        return ResponseEntity.ok(responses);
    }

    // Create Subject
    // POST url: http://localhost:8080/api/subjects
    @PostMapping
    public ResponseEntity<SubjectResponse> createSubject(@RequestBody SubjectRequest request) {
        SubjectResponse response = subjectService.addSubject(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    // Get Subject by subjectId
    // GET url: http://localhost:8080/api/subjects/2
    @GetMapping("/{id}")
    public ResponseEntity<SubjectResponse> getSubject(@PathVariable("id") Long subjectId) {
        SubjectResponse response = subjectService.getSubject(subjectId);
        if(response == null) return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    // Update Subject by subjectId
    // PUT url: http://localhost:8080/api/subjects/3
    @PutMapping("/{id}")
    public ResponseEntity<SubjectResponse> updateSubject(
            @PathVariable("id") Long subjectId,
            @RequestBody SubjectRequest updatedSubject) {
        SubjectResponse response = subjectService.updateSubject(subjectId, updatedSubject);
        if(response == null) return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    // Delete Subject by subjectId
    // DELETE url: http://localhost:8080/api/subjects/4
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteSubject(@PathVariable("id") Long subjectId) {
        boolean deleted = subjectService.deleteSubject(subjectId);
        if(deleted) return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Subject deleted successfully");

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Invalid Subject ID.");
    }
}

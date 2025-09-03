package com.devapp.studentms.controller;

import com.devapp.studentms.request.SubjectRequest;
import com.devapp.studentms.response.SubjectResponse;
import com.devapp.studentms.service.SubjectService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/subjects")
@Tag(name = "Subject APIs")
public class SubjectController {
    private final SubjectService subjectService;

    // Get all subjects
    // GET url: http://localhost:8080/api/subjects
    @GetMapping
    public ResponseEntity<?> fetchAllSubjects() {
        List<SubjectResponse> responses = subjectService.getAllSubjects();
        if(responses.isEmpty()) return ResponseEntity.ok("No subject record found in DB");
        return ResponseEntity.ok(responses);
    }

    // Create Subject
    // POST url: http://localhost:8080/api/subjects
    @PostMapping
    public ResponseEntity<?> createSubject(@RequestBody SubjectRequest request) {
        try {
            SubjectResponse response = subjectService.addSubject(request);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        }
        catch (IllegalArgumentException ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }

    // Get Subject by subjectId
    // GET url: http://localhost:8080/api/subjects/2
    @GetMapping("/{id}")
    public ResponseEntity<?> getSubject(@PathVariable("id") Long subjectId) {
        SubjectResponse response = subjectService.getSubject(subjectId);
        if(response == null) return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No subject record found in DB of subjectId: " + subjectId);

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    // Update Subject by subjectId
    // PUT url: http://localhost:8080/api/subjects/3
    @PutMapping("/{id}")
    public ResponseEntity<?> updateSubject(
            @PathVariable("id") Long subjectId,
            @RequestBody SubjectRequest updatedSubject) {
        try {
            SubjectResponse response = subjectService.updateSubject(subjectId, updatedSubject);
            if (response == null) return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Invalid subjectId: " + subjectId);

            return ResponseEntity.status(HttpStatus.OK).body(response);
        }
        catch (IllegalArgumentException ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }

    // Delete Subject by subjectId
    // DELETE url: http://localhost:8080/api/subjects/4
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteSubject(@PathVariable("id") Long subjectId) {
        boolean deleted = subjectService.deleteSubject(subjectId);
        if(deleted) return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Subject deleted successfully, subjectId: " + subjectId);

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Invalid SubjectId: " + subjectId);
    }
}
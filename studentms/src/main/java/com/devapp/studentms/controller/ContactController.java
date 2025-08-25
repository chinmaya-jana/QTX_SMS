package com.devapp.studentms.controller;

import com.devapp.studentms.request.ContactRequest;
import com.devapp.studentms.response.ContactResponse;
import com.devapp.studentms.service.ContactService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/contact")
public class ContactController {
    private final ContactService contactService;

    @GetMapping
    public ResponseEntity<List<ContactResponse>> fetchContacts() {
        List<ContactResponse> responses = contactService.fetchContacts();
        return ResponseEntity.ok(responses);
    }

    //example: http:localhost:8080/api/contact?studentId=1
    @GetMapping
    public ResponseEntity<ContactResponse> getContact(@RequestParam("studentId") Long studentId) {
        ContactResponse response = contactService.getContact(studentId);
        if(response == null) return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

        return ResponseEntity.ok(response);
    }

    // Updating contact details can be done by an authenticated student/user.
    //example: http://localhost:8080/api/contact?studentId=1
    @PutMapping
    public ResponseEntity<ContactResponse> updateContact(
            @RequestBody ContactRequest updatedContact,
            @RequestParam("studentId") Long studentId) {
        ContactResponse response = contactService.updateContact(updatedContact, studentId);

        if(response == null) return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

        return ResponseEntity.ok(response);
    }

    // example: http://localhost:8080/api/contact?studentId=1
    @DeleteMapping
    public ResponseEntity<String> deleteContact(@RequestParam("studentId") Long studentId) {
        boolean deleted = contactService.deleteContact(studentId);
        if(deleted) return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Contact details deleted successfully.");

        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
}

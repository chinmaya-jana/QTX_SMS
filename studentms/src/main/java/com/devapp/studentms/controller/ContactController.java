package com.devapp.studentms.controller;

import com.devapp.studentms.request.ContactRequest;
import com.devapp.studentms.response.ContactResponse;
import com.devapp.studentms.service.ContactService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/contact")
@Tag(name = "Contact APIs")
public class ContactController {
    private final ContactService contactService;

    // Get all contacts
    // GET url: http://localhost:8080/api/contact
    @GetMapping
    public ResponseEntity<List<ContactResponse>> fetchContacts() {
        List<ContactResponse> responses = contactService.fetchContacts();
        return ResponseEntity.ok(responses);
    }

    // Get contact by studentId
    // GET url: http:localhost:8080/api/contact?studentId=1
    @GetMapping(params = "studentId")
    public ResponseEntity<ContactResponse> getContact(@RequestParam Long studentId) {
        ContactResponse response = contactService.getContact(studentId);
        if(response == null) return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

        return ResponseEntity.ok(response);
    }

    // Update contact by studentId
    //PUT url: http://localhost:8080/api/contact?studentId=1
    @PutMapping(params = "studentId")
    public ResponseEntity<ContactResponse> updateContact(
            @RequestBody ContactRequest updatedContact,
            @RequestParam Long studentId) {
        ContactResponse response = contactService.updateContact(updatedContact, studentId);

        if(response == null) return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

        return ResponseEntity.ok(response);
    }
}
package com.devapp.studentms.mapper;

import com.devapp.studentms.model.Contact;
import com.devapp.studentms.model.Student;
import com.devapp.studentms.request.ContactRequest;
import com.devapp.studentms.response.ContactResponse;

public class ContactMapper {
    public static Contact toEntity(ContactRequest request, Student student) {
        if(request == null) return null;

        Contact contact = new Contact();
        contact.setEmail(request.getEmail());
        contact.setPhone(request.getPhone());
        contact.setParentPhone(request.getParentPhone());
        contact.setStudent(student);

        return contact;
    }

    public static ContactResponse toResponse(Contact contact) {
        if(contact == null) return null;

        return ContactResponse.builder()
                .studentId(contact.getStudent().getStudentId())
                .studentName(contact.getStudent().getFirstName() + " " + contact.getStudent().getLastName())
                .email(contact.getEmail())
                .phone(contact.getPhone())
                .parentPhone(contact.getParentPhone())
                .createdAt(contact.getCreatedAt())
                .updatedAt(contact.getUpdatedAt())
                .build();
    }
}

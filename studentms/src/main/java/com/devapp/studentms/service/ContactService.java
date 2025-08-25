package com.devapp.studentms.service;

import com.devapp.studentms.request.ContactRequest;
import com.devapp.studentms.response.ContactResponse;

import java.util.List;

public interface ContactService {
    List<ContactResponse> fetchContacts();

    ContactResponse getContact(Long studentId);

    ContactResponse updateContact(ContactRequest updatedContact, Long studentId);

    boolean deleteContact(Long studentId);
}

package com.devapp.studentms.service.impl;

import com.devapp.studentms.mapper.ContactMapper;
import com.devapp.studentms.model.Contact;
import com.devapp.studentms.repo.ContactRepository;
import com.devapp.studentms.repo.StudentRepository;
import com.devapp.studentms.request.ContactRequest;
import com.devapp.studentms.response.ContactResponse;
import com.devapp.studentms.service.ContactService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ContactServiceImpl implements ContactService {
    private final ContactRepository contactRepository;
    /*
    public ContactServiceImpl(ContactRepository contactRepository) {
        this.contactRepository = contactRepository;
    }
    */

    @Override
    public List<ContactResponse> fetchContacts() {
        return contactRepository.findAll()
                .stream()
                .map(ContactMapper::toResponse)
                .toList();
    }

    @Override
    public ContactResponse getContact(Long studentId) {
        Contact contact = (Contact) contactRepository.findByStudent_StudentId(studentId).orElse(null);
        return ContactMapper.toResponse(contact);
    }

    @Override
    public ContactResponse updateContact(ContactRequest updatedContact, Long studentId) {
        Contact contact = (Contact) contactRepository.findByStudent_StudentId(studentId).orElse(null);
        if(contact != null) {
            contact.setEmail(updatedContact.getEmail());
            contact.setPhone(updatedContact.getPhone());
            contact.setParentPhone(updatedContact.getParentPhone());
            Contact saved = contactRepository.save(contact);
            return ContactMapper.toResponse(saved);
        }
        return null;
    }

    @Override
    public boolean deleteContact(Long studentId) {
        return contactRepository.findByStudent_StudentId(studentId)
                .map(contact -> {
                    contactRepository.delete((Contact) contact);
                    return true;
                }).orElse(false);
    }
}

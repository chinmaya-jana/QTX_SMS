package com.devapp.studentms.service.impl;

import com.devapp.studentms.mapper.AddressMapper;
import com.devapp.studentms.mapper.ContactMapper;
import com.devapp.studentms.mapper.StudentMapper;
import com.devapp.studentms.model.Address;
import com.devapp.studentms.model.Contact;
import com.devapp.studentms.model.Course;
import com.devapp.studentms.model.Student;
import com.devapp.studentms.repo.AddressRepository;
import com.devapp.studentms.repo.ContactRepository;
import com.devapp.studentms.repo.CourseRepository;
import com.devapp.studentms.repo.StudentRepository;
import com.devapp.studentms.request.StudentRequest;
import com.devapp.studentms.response.StudentResponse;
import com.devapp.studentms.service.StudentService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class StudentServiceImpl implements StudentService {
    private final StudentRepository studentRepository;
    private final CourseRepository courseRepository;
    private final AddressRepository addressRepository;
    private final ContactRepository contactRepository;

    @Override
    public StudentResponse addStudent(StudentRequest request) {
        // validate courseId
        Course course = courseRepository.findById(request.getCourseId()).orElse(null);
        if(course == null) return null;

        // Student Entity
        Student student = StudentMapper.toEntity(request, course);
        Student savedStudent = studentRepository.save(student);

        // Address Entity
        Address address = AddressMapper.toEntity(request.getAddress(), savedStudent);
        addressRepository.save(address);

        // Contact Entity
        Contact contact = ContactMapper.toEntity(request.getContact(), savedStudent);
        contactRepository.save(contact);
        return StudentMapper.toResponse(student);
    }

    @Override
    public List<StudentResponse> getStudents() {
        return studentRepository.findAll()
                .stream()
                .map(StudentMapper:: toResponse)
                .toList();
    }

    @Override
    public StudentResponse getStudent(Long studentId) {
        return StudentMapper.toResponse(studentRepository.findById(studentId).orElse(null));
    }

    @Override
    public StudentResponse updateStudent(Long studentId, StudentRequest updatedStudent) {
        // Student will upgrade to next batch (important)
        Course course = courseRepository.findById(updatedStudent.getCourseId()).orElse(null);
        if(course == null) return null;

        return studentRepository.findById(studentId)
                .map(existingStudent -> {
                    existingStudent.setFirstName(updatedStudent.getFirstName());
                    existingStudent.setLastName(updatedStudent.getLastName());
                    existingStudent.setDob(updatedStudent.getDob());
                    existingStudent.setGender(updatedStudent.getGender().name());
                    existingStudent.setStatus(updatedStudent.getStatus().name());
                    existingStudent.setCourse(course);

                    Student saved = studentRepository.save(existingStudent);
                    return StudentMapper.toResponse(saved);
                }).orElse(null);
    }

    @Override
    public boolean deleteStudent(Long studentId) {
        Student student = studentRepository.findById(studentId).orElse(null);

        if(student != null) {
            studentRepository.deleteById(studentId);
            return true;
        }
        return false;
    }
}
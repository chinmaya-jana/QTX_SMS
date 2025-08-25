package com.devapp.studentms.service;

import com.devapp.studentms.request.StudentRequest;
import com.devapp.studentms.response.StudentResponse;

import java.util.List;

public interface StudentService {
    StudentResponse addStudent(StudentRequest request);

    List<StudentResponse> getStudents();

    StudentResponse getStudent(Long studentId);

    StudentResponse updateStudent(Long studentId, StudentRequest updatedStudent);

    boolean deleteStudent(Long studentId);
}

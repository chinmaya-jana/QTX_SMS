package com.devapp.studentms.service;

import com.devapp.studentms.request.SubjectRequest;
import com.devapp.studentms.response.SubjectResponse;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface SubjectService {
    List<SubjectResponse> getAllSubjects();

    SubjectResponse addSubject(SubjectRequest request);

    SubjectResponse getSubject(Long subjectId);

    SubjectResponse updateSubject(Long subjectId, SubjectRequest updatedSubject);

    boolean deleteSubject(Long subjectId);
}

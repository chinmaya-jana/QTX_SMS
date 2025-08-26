package com.devapp.studentms.mapper;

import com.devapp.studentms.model.Subject;
import com.devapp.studentms.request.SubjectRequest;
import com.devapp.studentms.response.SubjectResponse;

public class SubjectMapper {
    public static Subject toEntity(SubjectRequest request) {
        if (request == null) return null;

        Subject subject = new Subject();
        subject.setSubjectCode(request.getSubjectCode());
        subject.setSubjectTitle(request.getSubjectTitle());

        return subject;
    }

    public static SubjectResponse toResponse(Subject subject) {
        if(subject == null) return null;

        return SubjectResponse.builder()
                .subjectId(subject.getSubjectId())
                .subjectCode(subject.getSubjectCode())
                .subTitle(subject.getSubjectTitle())
                .createdAt(subject.getCreatedAt())
                .updatedAt(subject.getUpdatedAt())
                .build();
    }
}

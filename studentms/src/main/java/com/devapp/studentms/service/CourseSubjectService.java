package com.devapp.studentms.service;

import com.devapp.studentms.request.CourseSubjectRequest;
import com.devapp.studentms.response.CourseSubjectResponse;

import java.util.List;

public interface CourseSubjectService {
    List<CourseSubjectResponse> getAllCourseSubjects();

    CourseSubjectResponse addCourseSubject(Long courseId, Long subjectId, CourseSubjectRequest request);

    CourseSubjectResponse getCourseSubject(Long courseId, Long subjectId);

    CourseSubjectResponse updateCourseSubject(Long courseId, Long subjectId, CourseSubjectRequest updatedCourseSubject);

    boolean deleteCourseSubject(Long courseId, Long subjectId);
}

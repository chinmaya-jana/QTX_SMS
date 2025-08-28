package com.devapp.studentms.service;

import com.devapp.studentms.enums.Status;
import com.devapp.studentms.request.CourseRequest;
import com.devapp.studentms.response.CourseResponse;
import com.devapp.studentms.response.StudentResponse;
import com.devapp.studentms.response.SubjectResponse;

import java.util.List;

public interface CourseService {
    CourseResponse addCourse(CourseRequest request);

    List<CourseResponse> fetchCourses();

    CourseResponse fetchCourse(Long courseId);

    boolean deleteCourse(Long courseId);

    CourseResponse updateCourse(Long courseId, CourseRequest updatedRequest);

    List<StudentResponse> findAllStudentsByCourse(Long courseId);

    List<StudentResponse> findAllActiveStudentsByCourse(Long courseId, Status status);

    List<SubjectResponse> findAllSubjectsByCourse(Long courseId);
}

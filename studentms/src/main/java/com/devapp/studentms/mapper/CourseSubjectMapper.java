package com.devapp.studentms.mapper;

import com.devapp.studentms.id.CourseSubjectId;
import com.devapp.studentms.model.Course;
import com.devapp.studentms.model.CourseSubject;
import com.devapp.studentms.model.Subject;
import com.devapp.studentms.request.CourseSubjectRequest;
import com.devapp.studentms.response.CourseSubjectResponse;

public class CourseSubjectMapper {
    public static CourseSubject toEntity(CourseSubjectRequest request, Course course, Subject subject) {
        if(request == null || course == null || subject == null) return null;

        CourseSubject courseSubject = new CourseSubject();
        courseSubject.setId(new CourseSubjectId(course.getCourseId(), subject.getSubjectId()));
        courseSubject.setCourse(course);
        courseSubject.setSubject(subject);
        courseSubject.setPaperType(request.getPaperType().name());
        courseSubject.setSemester(request.getSemester());
        courseSubject.setCredits(request.getCredits());
        courseSubject.setStatus(request.getStatus().name());
        courseSubject.setDescription(request.getDescription());

        return courseSubject;
    }
    public static CourseSubjectResponse toResponse(CourseSubject courseSubject) {
        if(courseSubject == null) return null;

        return CourseSubjectResponse.builder()
                .courseSubjectId(courseSubject.getId())
                .courseTitle(courseSubject.getCourse().getCourseTitle())
                .subjectTitle(courseSubject.getSubject().getSubjectTitle())
                .paperType(courseSubject.getPaperType().name())
                .semester(courseSubject.getSemester())
                .credits(courseSubject.getCredits())
                .status(courseSubject.getStatus().name())
                .description(courseSubject.getDescription())
                .createdAt(courseSubject.getCreatedAt())
                .updatedAt(courseSubject.getUpdatedAt())
                .build();
    }
}
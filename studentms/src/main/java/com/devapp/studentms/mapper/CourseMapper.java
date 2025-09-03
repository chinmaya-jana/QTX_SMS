package com.devapp.studentms.mapper;

import com.devapp.studentms.enums.Status;
import com.devapp.studentms.model.Course;
import com.devapp.studentms.request.CourseRequest;
import com.devapp.studentms.response.CourseResponse;

import java.time.Period;

public class CourseMapper {
    // Convert Course Request -> Course Entity
    public static Course toEntity(CourseRequest request) {
        if(request == null) return null;

        Course course = new Course();
        course.setCourseTitle(request.getCourseTitle());

        if(request.getDuration() != null) {
            course.setDuration(Period.parse(request.getDuration()));
        }

        course.setTotalSemester(request.getTotalSemester());
        course.setStatus(String.valueOf(request.getStatus()));

        return course;
    }

    public static CourseResponse toResponse(Course course) {
        if (course == null) return null;

        return CourseResponse.builder()
                .courseId(course.getCourseId())
                .courseTitle(course.getCourseTitle())
                .duration(CourseResponse.formatDuration(course.getDuration()))
                .totalSemester(course.getTotalSemester())
                .status(course.getStatus())
                .createdAt(course.getCreatedAt())
                .updatedAt(course.getUpdatedAt())
                .build();
    }
}
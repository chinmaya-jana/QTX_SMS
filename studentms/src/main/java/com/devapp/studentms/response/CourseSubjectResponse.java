package com.devapp.studentms.response;

import com.devapp.studentms.id.CourseSubjectId;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Builder
@Getter
public class CourseSubjectResponse {
    private CourseSubjectId courseSubjectId;
    private String courseTitle;
    private String subjectTitle;
    private String paperType;
    private Integer semester;
    private Float credits;
    private String status;
    private String description;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
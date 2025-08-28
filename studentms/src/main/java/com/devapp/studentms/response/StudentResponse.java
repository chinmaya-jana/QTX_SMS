package com.devapp.studentms.response;

import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Builder
@Getter
public class StudentResponse {
    private Long studentId;
    private String studentName;
    private LocalDate dob;
    private String gender;
    private String status;

    private Long courseId;
    private String courseTitle;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
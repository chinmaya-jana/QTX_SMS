package com.devapp.studentms.response;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Builder
@Getter
public class SubjectResponse {
    private Long subjectId;
    private String subjectCode;
    private String subTitle;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
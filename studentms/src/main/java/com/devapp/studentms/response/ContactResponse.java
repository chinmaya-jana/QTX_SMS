package com.devapp.studentms.response;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Builder
@Getter
public class ContactResponse {
    private Long studentId;
    private String studentName;

    private String email;
    private String phone;
    private String parentPhone;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}

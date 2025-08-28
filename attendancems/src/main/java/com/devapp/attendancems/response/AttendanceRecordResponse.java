package com.devapp.attendancems.response;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class AttendanceRecordResponse {
    private Long id;
    private Long sessionId;
    private Long studentId;
    private String status;
}

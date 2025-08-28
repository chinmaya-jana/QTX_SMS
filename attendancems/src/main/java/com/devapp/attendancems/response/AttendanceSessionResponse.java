package com.devapp.attendancems.response;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Builder
@Getter
public class AttendanceSessionResponse {
    private Long sessionId;
    private Long courseId;
    private Long subjectId;
    private LocalDate date;
    private boolean isHoliday;
    private String message;
}

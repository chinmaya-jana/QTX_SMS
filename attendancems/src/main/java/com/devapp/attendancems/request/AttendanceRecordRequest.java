package com.devapp.attendancems.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class AttendanceRecordRequest {
    private Long sessionId;
    private Long studentId;
    private String status;    // PRESENT or ABSENT
}

package com.devapp.attendancems.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class AttendanceSessionRequest {
    private Long courseId;
    private Long subjectId;
    private LocalDate date;
}

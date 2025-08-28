package com.devapp.attendancems.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "attendance_session",
        uniqueConstraints = @UniqueConstraint(columnNames = {"courseId", "subjectId", "date"}))
public class AttendanceSession {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long sessionId;
    private Long courseId;
    private Long subjectId;
    private Boolean isHoliday = false;
}

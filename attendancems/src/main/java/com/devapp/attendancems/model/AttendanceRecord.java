package com.devapp.attendancems.model;

import com.devapp.attendancems.enums.AttendanceStatus;
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
@Table(name = "attendance_record",
        uniqueConstraints = @UniqueConstraint(columnNames = {"sessionId", "studentId"}))
public class AttendanceRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long sessionId;
    private Long studentId;

    @Enumerated(EnumType.STRING)
    private AttendanceStatus status = AttendanceStatus.ABSENT;  // Default ABSENT
}
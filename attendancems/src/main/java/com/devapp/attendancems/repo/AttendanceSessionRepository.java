package com.devapp.attendancems.repo;

import com.devapp.attendancems.model.AttendanceSession;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AttendanceSessionRepository extends JpaRepository<AttendanceSession, Long> {
}

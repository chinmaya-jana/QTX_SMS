package com.devapp.attendancems.repo;

import com.devapp.attendancems.model.Holiday;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HolidayRepository extends JpaRepository<Holiday, Long> {
}

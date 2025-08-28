package com.devapp.attendancems.response;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Builder
@Getter
public class HolidayResponse {
    private Long id;
    private LocalDate holidayDate;
    private String description;
}

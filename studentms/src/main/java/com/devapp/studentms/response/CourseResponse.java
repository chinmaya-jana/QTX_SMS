package com.devapp.studentms.response;

import com.devapp.studentms.enums.Status;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.time.Period;

@Builder
@Getter
public class CourseResponse {
    private final Long courseId;
    private final String courseTitle;
    private final String duration;   //e.g: "3 years 4 months 15 days"
    private final Integer totalSemester;
    private final Status status;

    private final LocalDateTime createdAt;
    private final LocalDateTime updatedAt;

    // Utility method to format Period into "X years Y months Z days"
    public static String formatDuration(Period period) {
        if(period == null) return null;

        StringBuilder sb = new StringBuilder();

        if (period.getYears() > 0) sb.append(period.getYears()).append(period.getYears() == 1 ? " year " : " years ");
        if (period.getMonths() > 0) sb.append(period.getMonths()).append(period.getMonths() == 1 ? " month " : " months ");
        if (period.getDays() > 0) sb.append(period.getDays()).append(period.getDays() == 1 ? " day " : " days ");

        return sb.toString().trim();
    }
}
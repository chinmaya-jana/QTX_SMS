package com.devapp.studentms.request;

import com.devapp.studentms.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class CourseRequest {
    private String courseTitle;
    private String duration;
    private Integer totalSemester;
    private Status status;
}

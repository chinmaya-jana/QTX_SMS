package com.devapp.studentms.request;

import com.devapp.studentms.enums.PaperType;
import com.devapp.studentms.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CourseSubjectRequest {
    private PaperType paperType;
    private Integer semester;
    private Float credits;
    private Status status;
    private String description;
}

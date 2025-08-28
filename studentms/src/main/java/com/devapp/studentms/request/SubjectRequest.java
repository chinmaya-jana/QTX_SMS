package com.devapp.studentms.request;

import com.devapp.studentms.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class SubjectRequest {
    private String subjectCode;
    private String subjectTitle;
}
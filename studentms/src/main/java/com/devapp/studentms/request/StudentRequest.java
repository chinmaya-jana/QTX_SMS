package com.devapp.studentms.request;

import com.devapp.studentms.enums.Gender;
import com.devapp.studentms.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class StudentRequest {
    // Student fields
    private String firstName;
    private String lastName;
    private LocalDate dob;
    private Gender gender;
    private Status status;

    //FK
    private Long courseId;

    private ContactRequest contact;
    private AddressRequest address;
}

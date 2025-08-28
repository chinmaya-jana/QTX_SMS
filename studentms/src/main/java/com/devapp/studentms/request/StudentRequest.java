package com.devapp.studentms.request;

import com.devapp.studentms.enums.Gender;
import com.devapp.studentms.enums.Status;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
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
    @NotEmpty
    @Schema(description = "Student's First Name")
    private String firstName;
    @Schema(description = "Student's Last Name")
    private String lastName;
    private LocalDate dob;
    @Schema(description = "Gender should be either Male or Female")
    private Gender gender;
    @Schema(description = "Status should be either Active or Inactive")
    private Status status;

    //FK
    @NotEmpty
    private Long courseId;

    private ContactRequest contact;
    private AddressRequest address;
}
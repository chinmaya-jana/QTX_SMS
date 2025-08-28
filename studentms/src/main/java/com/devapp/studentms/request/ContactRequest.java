package com.devapp.studentms.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ContactRequest {
    private String email;
    private String phone;
    private String parentPhone;
}
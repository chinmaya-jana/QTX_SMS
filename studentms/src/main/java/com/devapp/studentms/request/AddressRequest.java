package com.devapp.studentms.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class AddressRequest {
    private String city;
    private String district;
    private String state;
    private String country;
    private Integer zip;
}

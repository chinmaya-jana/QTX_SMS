package com.devapp.studentms.response;

import lombok.*;

import java.time.LocalDateTime;

@Builder
@Getter
public class AddressResponse {
    private Long studentId;
    private String studentName;

    private String city;
    private String district;
    private String state;
    private String country;
    private Integer zip;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
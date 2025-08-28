package com.devapp.studentms.mapper;

import com.devapp.studentms.model.Address;
import com.devapp.studentms.model.Student;
import com.devapp.studentms.request.AddressRequest;
import com.devapp.studentms.response.AddressResponse;

public class AddressMapper {
    public static Address toEntity(AddressRequest request, Student student) {
        if(request == null) return null;

        Address address = new Address();
        address.setCity(request.getCity());
        address.setDistrict(request.getDistrict());
        address.setState(request.getState());
        address.setCountry(request.getCountry());
        address.setZip(request.getZip());
        address.setStudent(student);

        return address;
    }

    public static AddressResponse toResponse(Address address) {
        if(address == null) return null;

        return AddressResponse.builder()
                .studentId(address.getStudent().getStudentId())
                .studentName(address.getStudent().getFirstName())
                .city(address.getCity())
                .district(address.getDistrict())
                .state(address.getState())
                .country(address.getCountry())
                .zip(address.getZip())
                .createdAt(address.getCreatedAt())
                .updatedAt(address.getUpdatedAt())
                .build();
    }
}
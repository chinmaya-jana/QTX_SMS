package com.devapp.studentms.service;

import com.devapp.studentms.request.AddressRequest;
import com.devapp.studentms.response.AddressResponse;

import java.util.List;

public interface AddressService {
    List<AddressResponse> fetchAddresses();

    AddressResponse getAddress(Long studentId);

    AddressResponse updateAddress(Long studentId, AddressRequest updatedAddress);
}

package com.devapp.studentms.service.impl;

import com.devapp.studentms.mapper.AddressMapper;
import com.devapp.studentms.model.Address;
import com.devapp.studentms.repo.AddressRepository;
import com.devapp.studentms.request.AddressRequest;
import com.devapp.studentms.response.AddressResponse;
import com.devapp.studentms.service.AddressService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class AddressServiceImpl implements AddressService {
    private final AddressRepository addressRepository;

    @Override
    public List<AddressResponse> fetchAddresses() {
        return addressRepository.findAll()
                .stream()
                .map(AddressMapper::toResponse)
                .toList();
    }

    @Override
    public AddressResponse getAddress(Long studentId) {
        Address address = (Address) addressRepository.findByStudent_StudentId(studentId).orElse(null);

        return AddressMapper.toResponse(address);
    }

    @Override
    public AddressResponse updateAddress(Long studentId, AddressRequest updatedAddress) {
        Address address = (Address) addressRepository.findByStudent_StudentId(studentId).orElse(null);

        if(address != null) {
            address.setCity(updatedAddress.getCity());
            address.setDistrict(updatedAddress.getDistrict());
            address.setState(updatedAddress.getState());
            address.setCountry(updatedAddress.getCountry());
            address.setZip(updatedAddress.getZip());
            Address saved = addressRepository.save(address);
            return AddressMapper.toResponse(saved);
        }
        return null;
    }
}

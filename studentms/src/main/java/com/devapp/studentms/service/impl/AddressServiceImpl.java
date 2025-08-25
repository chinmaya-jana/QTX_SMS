package com.devapp.studentms.service.impl;

import com.devapp.studentms.repo.AddressRepository;
import com.devapp.studentms.service.AddressService;
import org.springframework.stereotype.Service;

@Service
public class AddressServiceImpl implements AddressService {
    private final AddressRepository addressRepository;

    public AddressServiceImpl(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }
}

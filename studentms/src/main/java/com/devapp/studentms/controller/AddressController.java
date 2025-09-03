package com.devapp.studentms.controller;

import com.devapp.studentms.request.AddressRequest;
import com.devapp.studentms.response.AddressResponse;
import com.devapp.studentms.service.AddressService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/address")
@Tag(name = "Address APIs")
public class AddressController {
    private final AddressService addressService;

    // Get all addresses
    // GET url: http://localhost:8080/api/address
    @GetMapping
    public ResponseEntity<?> fetchAddresses() {
        List<AddressResponse> responses = addressService.fetchAddresses();
        if(responses.isEmpty()) return ResponseEntity.status(HttpStatus.OK).body("No record found, that means empty student-details");
        return ResponseEntity.status(HttpStatus.OK).body(responses);
    }

    // Get address by studentId
    // GET url: http://localhost:8080/api/address?studentId=2
    @GetMapping(params = "studentId")
    public ResponseEntity<?> getAddress(@RequestParam Long studentId) {
        AddressResponse response = addressService.getAddress(studentId);
        if(response == null) return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Invalid studentId: " + studentId);

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    // Update address by studentId
    // PUT url: http://localhost:8080/api/address?studentId=2
    @PutMapping(params = "studentId")
    public ResponseEntity<?> updateAddress(
            @RequestParam Long studentId,
            @RequestBody AddressRequest updatedAddress) {
        try {
            AddressResponse response = addressService.updateAddress(studentId, updatedAddress);
            if (response == null)
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Invalid studentId: " + studentId);

            return ResponseEntity.status(HttpStatus.OK).body(response);
        }
        catch (IllegalArgumentException ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }
}
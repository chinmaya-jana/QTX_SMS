package com.devapp.studentms.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum Status {
    ACTIVE("Active"),
    INACTIVE("Inactive");

    private final String label;

    Status(String label) {
        this.label = label;
    }

    @JsonValue   //for response
    public String getLable() {
        return label;
    }

    @JsonCreator   //for request
    public static Status fromString(String input) {
        if (input == null) throw new IllegalArgumentException("Status cannot be null");
        return switch (input.trim().toLowerCase()) {
            case "active" -> ACTIVE;
            case "inactive" -> INACTIVE;
            default -> throw new IllegalArgumentException("Invalid status : " + input);
        };
    }
}

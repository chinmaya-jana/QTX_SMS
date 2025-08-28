package com.devapp.studentms.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum Gender {
    MALE("Male"),
    FEMALE("Female");

    private final String label;

    Gender(String label) {
        this.label = label;
    }

    @JsonValue
    public String getLable() {
        return label;
    }

    @JsonCreator
    public static Gender fromString(String value) {
        if (value == null) throw new IllegalArgumentException("Gender cannot be null");
        return switch (value.trim().toLowerCase()) {
            case "male", "m" -> MALE;
            case "female", "f" -> FEMALE;
            default -> throw new IllegalArgumentException("Invalid gender value: " + value);
        };
    }
}
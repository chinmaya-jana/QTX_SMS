package com.devapp.studentms.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum PaperType {
    CORE("Core"),
    ELECTIVE("Elective");

    private final String label;

    PaperType(String label) {
        this.label = label;
    }

    @JsonValue
    public String getLabel() {
        return label;
    }

    @JsonCreator
    public static PaperType fromString(String value) {
        if (value == null) throw new IllegalArgumentException("Paper_type cannot be null");

        return switch (value.trim().toLowerCase()) {
            case "core" -> CORE;
            case "elective" -> ELECTIVE;
            default -> throw new  IllegalArgumentException("Invalid paper_type value: " + value);
        };
    }
}

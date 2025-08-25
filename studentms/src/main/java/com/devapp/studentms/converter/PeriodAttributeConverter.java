package com.devapp.studentms.converter;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.time.Period;

@Converter(autoApply = true)
public class PeriodAttributeConverter implements AttributeConverter<Period, String> {
    @Override
    public String convertToDatabaseColumn(Period attribute) {
        return attribute == null ? null : attribute.toString();  //e.g. "P1Y6M15D"
    }

    @Override
    public Period convertToEntityAttribute(String dbData) {
        return dbData == null ? null : Period.parse(dbData);
    }
}

package com.devapp.studentms.mapper;

import com.devapp.studentms.model.Course;
import com.devapp.studentms.model.Student;
import com.devapp.studentms.request.StudentRequest;
import com.devapp.studentms.response.StudentResponse;

public class StudentMapper {
    public static Student toEntity(StudentRequest request, Course course) {
        if(request == null) return null;

        Student student = new Student();
        student.setFirstName(request.getFirstName());
        student.setLastName(request.getLastName());
        student.setDob(request.getDob());
        student.setGender(request.getGender().name());
        student.setStatus(request.getStatus().name());
        student.setCourse(course);

        return student;
    }

    public static StudentResponse toResponse(Student student) {
        if(student == null) return null;

        return StudentResponse.builder()
                .studentId(student.getStudentId())
                .studentName(student.getFirstName() + " " + student.getLastName())
                .dob(student.getDob())
                .gender(student.getGender().getLable())
                .status(student.getStatus().getLable())
                .courseId(student.getCourse().getCourseId())
                .courseTitle(student.getCourse().getCourseTitle())
                .createdAt(student.getCreatedAt())
                .updatedAt(student.getUpdatedAt())
                .build();
    }
}
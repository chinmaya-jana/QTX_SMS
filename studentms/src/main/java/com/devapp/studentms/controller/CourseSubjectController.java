package com.devapp.studentms.controller;

import com.devapp.studentms.service.CourseSubjectService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/api/course-subject")
public class CourseSubjectController {
    private final CourseSubjectService courseSubjectService;
}

package com.devapp.studentms.service.impl;

import com.devapp.studentms.mapper.CourseMapper;
import com.devapp.studentms.model.Course;
import com.devapp.studentms.repo.CourseRepository;
import com.devapp.studentms.request.CourseRequest;
import com.devapp.studentms.response.CourseResponse;
import com.devapp.studentms.service.CourseService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Period;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class CourseServiceImpl implements CourseService {
    private final CourseRepository courseRepository;
    /*
    public CourseServiceImpl(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }
    */

    @Override
    public CourseResponse addCourse(CourseRequest request) {
        Course course = CourseMapper.toEntity(request);
        Course saved = courseRepository.save(course);
        return CourseMapper.toResponse(saved);
    }

    @Override
    public List<CourseResponse> fetchCourses() {
        /*
        List<Course> courses = courseRepository.findAll();
        List<CourseResponse> responses = new ArrayList<>();
        for(Course course : courses) {
            responses.add(CourseMapper.toResponse(course));
        }
        return responses;
        */
        return courseRepository.findAll()
                .stream()
                .map(CourseMapper::toResponse)
                .toList();
    }

    @Override
    public CourseResponse fetchCourse(Long courseId) {
        return CourseMapper.toResponse(courseRepository.findById(courseId).orElse(null));
    }

    @Override
    public boolean deleteCourse(Long courseId) {
        Course course = courseRepository.findById(courseId).orElse(null);
        if (course != null) {
            courseRepository.deleteById(courseId);
            return true;
        }
        return false;
    }

    @Override
    public CourseResponse updateCourse(Long courseId, CourseRequest updatedRequest) {
        return courseRepository.findById(courseId)
                .map(existingCourse -> {
                    existingCourse.setCourseTitle(updatedRequest.getCourseTitle());
                    existingCourse.setDuration(Period.parse(updatedRequest.getDuration()));
                    existingCourse.setTotalSemester(updatedRequest.getTotalSemester());
                    existingCourse.setStatus(String.valueOf(updatedRequest.getStatus()));

                    Course saved = courseRepository.save(existingCourse);
                    return CourseMapper.toResponse(saved);
                }).orElse(null);
    }
}

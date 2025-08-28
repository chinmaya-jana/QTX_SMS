package com.devapp.studentms.service.impl;

import com.devapp.studentms.enums.Status;
import com.devapp.studentms.mapper.CourseMapper;
import com.devapp.studentms.mapper.StudentMapper;
import com.devapp.studentms.mapper.SubjectMapper;
import com.devapp.studentms.model.Course;
import com.devapp.studentms.repo.CourseRepository;
import com.devapp.studentms.request.CourseRequest;
import com.devapp.studentms.response.CourseResponse;
import com.devapp.studentms.response.StudentResponse;
import com.devapp.studentms.response.SubjectResponse;
import com.devapp.studentms.service.CourseService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Period;
import java.util.List;

@Service
@AllArgsConstructor
public class CourseServiceImpl implements CourseService {
    private final CourseRepository courseRepository;

    @Override
    public CourseResponse addCourse(CourseRequest request) {
        Course course = CourseMapper.toEntity(request);
        Course saved = courseRepository.save(course);
        return CourseMapper.toResponse(saved);
    }

    @Override
    public List<CourseResponse> fetchCourses() {
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

    // ---------------------------ADVANCE API END POINTS----------------------------------

    @Override
    public List<StudentResponse> findAllStudentsByCourse(Long courseId) {
        Course course = courseRepository.findById(courseId).orElse(null);
        if(course == null) return null;

        return course.getStudents()
                .stream()
                .map(StudentMapper::toResponse)
                .toList();
    }

    @Override
    public List<StudentResponse> findAllActiveStudentsByCourse(Long courseId, Status status) {
        Course course = courseRepository.findById(courseId).orElse(null);
        if(course == null) return null;

        return course.getStudents()
                .stream()
                .filter(student -> student.getStatus() == status)
                .map(StudentMapper::toResponse)
                .toList();
    }

    @Override
    public List<SubjectResponse> findAllSubjectsByCourse(Long courseId) {
        Course course = courseRepository.findById(courseId).orElse(null);
        if(course == null) return null;

        return course.getCourseSubjectSet()
                .stream()
                .map(cs -> SubjectMapper.toResponse(cs.getSubject()))
                .toList();
    }
}
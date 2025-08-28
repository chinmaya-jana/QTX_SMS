package com.devapp.studentms.service.impl;

import com.devapp.studentms.id.CourseSubjectId;
import com.devapp.studentms.mapper.CourseSubjectMapper;
import com.devapp.studentms.model.Course;
import com.devapp.studentms.model.CourseSubject;
import com.devapp.studentms.model.Subject;
import com.devapp.studentms.repo.CourseRepository;
import com.devapp.studentms.repo.CourseSubjectRepository;
import com.devapp.studentms.repo.SubjectRepository;
import com.devapp.studentms.request.CourseSubjectRequest;
import com.devapp.studentms.response.CourseSubjectResponse;
import com.devapp.studentms.service.CourseSubjectService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CourseSubjectServiceImpl implements CourseSubjectService {
    private final CourseSubjectRepository courseSubjectRepository;
    private final CourseRepository courseRepository;
    private final SubjectRepository subjectRepository;

    @Override
    public List<CourseSubjectResponse> getAllCourseSubjects() {
        return courseSubjectRepository.findAll()
                .stream()
                .map(CourseSubjectMapper::toResponse)
                .toList();
    }

    @Override
    public CourseSubjectResponse addCourseSubject(Long courseId, Long subjectId, CourseSubjectRequest request) {
        // convert to entity
        Course course = courseRepository.findById(courseId).orElse(null);
        Subject subject = subjectRepository.findById(subjectId).orElse(null);
        CourseSubject entity = CourseSubjectMapper.toEntity(request, course, subject);
        if(entity == null) return null;

        CourseSubject saved = courseSubjectRepository.save(entity);
        return CourseSubjectMapper.toResponse(saved);
    }

    @Override
    public CourseSubjectResponse getCourseSubject(Long courseId, Long subjectId) {
        CourseSubjectId id = new CourseSubjectId(courseId, subjectId);
        CourseSubject courseSubject = courseSubjectRepository.findById(id).orElse(null);
        return CourseSubjectMapper.toResponse(courseSubject);
    }

    @Override
    public CourseSubjectResponse updateCourseSubject(Long courseId, Long subjectId, CourseSubjectRequest updatedCourseSubject) {
        CourseSubjectId id = new CourseSubjectId(courseId, subjectId);
        CourseSubject courseSubject = courseSubjectRepository.findById(id).orElse(null);
        if(courseSubject != null) {
            courseSubject.setPaperType(updatedCourseSubject.getPaperType().name());
            courseSubject.setSemester(updatedCourseSubject.getSemester());
            courseSubject.setCredits(updatedCourseSubject.getCredits());
            courseSubject.setStatus(updatedCourseSubject.getStatus().name());
            courseSubject.setDescription(updatedCourseSubject.getDescription());

            CourseSubject saved = courseSubjectRepository.save(courseSubject);
            return CourseSubjectMapper.toResponse(saved);
        }
        return null;
    }

    @Override
    public boolean deleteCourseSubject(Long courseId, Long subjectId) {
        CourseSubjectId id = new CourseSubjectId(courseId, subjectId);
        CourseSubject courseSubject = courseSubjectRepository.findById(id).orElse(null);
        if(courseSubject != null) {
            courseSubjectRepository.delete(courseSubject);
            return true;
        }
        return false;
    }
}
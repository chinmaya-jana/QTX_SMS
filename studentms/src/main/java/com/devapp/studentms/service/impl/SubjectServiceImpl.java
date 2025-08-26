package com.devapp.studentms.service.impl;

import com.devapp.studentms.mapper.SubjectMapper;
import com.devapp.studentms.model.Subject;
import com.devapp.studentms.repo.SubjectRepository;
import com.devapp.studentms.request.SubjectRequest;
import com.devapp.studentms.response.SubjectResponse;
import com.devapp.studentms.service.SubjectService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class SubjectServiceImpl implements SubjectService {
    private final SubjectRepository subjectRepository;

    @Override
    public List<SubjectResponse> getAllSubjects() {
        return subjectRepository.findAll()
                .stream()
                .map(SubjectMapper::toResponse)
                .toList();
    }

    @Override
    public SubjectResponse addSubject(SubjectRequest request) {
        Subject subject = SubjectMapper.toEntity(request);
        Subject saved = subjectRepository.save(subject);
        return SubjectMapper.toResponse(saved);
    }

    @Override
    public SubjectResponse getSubject(Long subjectId) {
        return SubjectMapper.toResponse(subjectRepository.findById(subjectId).orElse(null));
    }

    @Override
    public SubjectResponse updateSubject(Long subjectId, SubjectRequest updatedSubject) {
        Subject subject = subjectRepository.findById(subjectId).orElse(null);
        if(subject != null) {
            subject.setSubjectCode(updatedSubject.getSubjectCode());
            subject.setSubjectTitle(updatedSubject.getSubjectTitle());

            Subject saved = subjectRepository.save(subject);
            return SubjectMapper.toResponse(saved);
        }
        return null;
    }

    @Override
    public boolean deleteSubject(Long subjectId) {
        return subjectRepository.findById(subjectId)
                .map(subject -> {
                    subjectRepository.delete(subject);
                    return true;
                }).orElse(false);
    }
}

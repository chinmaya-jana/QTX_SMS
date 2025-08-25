package com.devapp.studentms.service.impl;

import com.devapp.studentms.repo.SubjectRepository;
import com.devapp.studentms.service.SubjectService;
import org.springframework.stereotype.Service;

@Service
public class SubjectServiceImpl implements SubjectService {
    private final SubjectRepository subjectRepository;

    public SubjectServiceImpl(SubjectRepository subjectRepository) {
        this.subjectRepository = subjectRepository;
    }
}

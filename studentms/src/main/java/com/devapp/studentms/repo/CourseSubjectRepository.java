package com.devapp.studentms.repo;

import com.devapp.studentms.id.CourseSubjectId;
import com.devapp.studentms.model.CourseSubject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseSubjectRepository extends JpaRepository<CourseSubject, CourseSubjectId> {
}

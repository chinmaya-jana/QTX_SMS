package com.devapp.studentms.model;

import com.devapp.studentms.enums.PaperType;
import com.devapp.studentms.enums.Status;
import com.devapp.studentms.id.CourseSubjectId;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "course_subject")
public class CourseSubject {
    @EmbeddedId
    private CourseSubjectId id;  // composite primary key

    @Enumerated(EnumType.STRING)
    @Column(name = "paper_type", nullable = false)
    private PaperType paperType;

    @Column(nullable = false)
    private Integer semester;

    @Column(nullable = false)
    private Float credits;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Status status;

    @Column(length = 500)
    private String description;

    // ---------------Created_At & Updated_At-----------------
    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    // -------------------------Relationship: FK fields---------------
    @ManyToOne
    @MapsId("courseId")      // maps courseId in CourseSubjectId
    @JoinColumn(name = "course_id", nullable = false)
    private Course course;

    @ManyToOne
    @MapsId("subjectId")     // maps subjectId in CourseSubjectId
    @JoinColumn(name = "subject_id", nullable = false)
    private Subject subject;

    // override the setter method for paper type
    public void setPaperType(String paperTypeInput) {
        this.paperType = PaperType.fromString(paperTypeInput);
    }

    public void setStatus(String statusInput) {
        this.status = Status.fromString(statusInput);
    }
}

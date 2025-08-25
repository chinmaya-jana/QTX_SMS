package com.devapp.studentms.model;

import com.devapp.studentms.converter.PeriodAttributeConverter;
import com.devapp.studentms.enums.Status;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.time.Period;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "courses")
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "course_id")
    private Long courseId;

    @Column(name = "course_title", nullable = false, length = 100)
    private String courseTitle;

    @Convert(converter = PeriodAttributeConverter.class)
    @Column(nullable = false)
    private Period duration;

    @Column(name = "total_semester", nullable = false)
    private Integer totalSemester;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Status status;

    // --------Created_At & Updated_At-----------
    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    // ------------------RELATIONSHIP-------------------------
    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Student> students;

    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<CourseSubject> courseSubjectSet = new HashSet<>();

    // override the setter method for the status
    public void setStatus(String statusInput) {
        this.status = Status.fromString(statusInput);
    }
}

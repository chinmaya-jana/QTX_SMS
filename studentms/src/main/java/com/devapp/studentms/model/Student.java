package com.devapp.studentms.model;

import com.devapp.studentms.enums.Gender;
import com.devapp.studentms.enums.Status;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name="students")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "student_id")
    private Long studentId;

    @Column(name = "first_name", nullable = false, length = 50)
    private String firstName;

    @Column(name="last_name", length = 50)
    private String lastName;

    @Column(nullable = false)
    private LocalDate dob;    // use case for the age

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Gender gender;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Status status;

    // ----------------Created_At & Updated_At--------------
    @CreationTimestamp
    @Column(name="created_at", updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name="updated_at")
    private LocalDateTime updatedAt;

    // ---------------Relationship: FK field and related table-------------
    @ManyToOne
    @JoinColumn(name="course_id", nullable = false)
    private Course course;

    @OneToOne(mappedBy = "student", cascade = CascadeType.ALL, orphanRemoval = true)
    private Contact contact;

    @OneToOne(mappedBy = "student", cascade = CascadeType.ALL, orphanRemoval = true)
    private Address address;

    // override the setter method for the gender
    public void setGender(String genderInput) {
        this.gender = Gender.fromString(genderInput);
    }

    public void setStatus(String statusInput) {
        this.status = Status.fromString(statusInput);
    }
}
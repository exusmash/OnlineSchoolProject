package com.example.onlineschoolproject.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "Teachers")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SequenceGenerator(name = "default_generator", sequenceName = "teachers_sequence", allocationSize = 1)
public class Teacher extends GenericModel {
    @Column(name = "teacher_fio", nullable = false)
    private String teacherFIO;

    @Column(name = "description", nullable = false)
    private String description;

    @OneToMany(mappedBy = "teacher")
    private List<Course> courses;

    @Column(name = "subject", nullable = false)
    @Enumerated(value = EnumType.STRING)
    private Subject subject;

    @OneToMany(mappedBy = "teacher")
    private List<Webinar> webinars;
}

package com.example.onlineschoolproject.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "Webinars")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SequenceGenerator(name = "default_generator", sequenceName = "webinar_sequence", allocationSize = 1)
public class Webinar extends GenericModel {
    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "length", nullable = false)
    private Integer length;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "start_date", nullable = false)
    private LocalDateTime startDate;

    @Column(name = "end_date", nullable = false)
    private LocalDateTime endDate;

    @ManyToMany
    @JoinTable(name = "user_webinars",
            joinColumns = @JoinColumn(name = "webinar_id"),
            foreignKey = @ForeignKey(name = "FK_WEBINARS_USERS"),
            inverseJoinColumns = @JoinColumn(name = "user_id"),
            inverseForeignKey = @ForeignKey(name = "FK_USERS_WEBINARS"))
    private List<User> users;

    @Column(name = "subject", nullable = false)
    @Enumerated(value = EnumType.STRING)
    private Subject subject;

    @ManyToOne
    @JoinColumn(name = "course_id", nullable = false,
            foreignKey = @ForeignKey(name = "FK_COURSE_INFO_WEBINAR"))
    private Course course;

    @ManyToOne
    @JoinColumn(name = "teacher_id", nullable = false,
            foreignKey = @ForeignKey(name = "FK_TEACHER_INFO_WEBINAR"))
    private Teacher teacher;
}

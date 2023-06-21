package com.example.onlineschoolproject.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "Courses")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SequenceGenerator(name = "default_generator", sequenceName = "courses_sequence", allocationSize = 1)
public class Course extends GenericModel {
    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "start_date", nullable = false)
    private LocalDateTime startDate;

    @Column(name = "end_date", nullable = false)
    private LocalDateTime endDate;

    @Column(name = "price", nullable = false)
    private Integer price;

    @Column(name = "duration")
    private Integer duration;

    @ManyToMany()
    @JoinTable(name = "user_courses",
            joinColumns = @JoinColumn(name = "course_id"),
            foreignKey = @ForeignKey(name = "FK_COURSES_USERS"),
            inverseJoinColumns = @JoinColumn(name = "user_id"),
            inverseForeignKey = @ForeignKey(name = "FK_USERS_COURSES")
    )
    private List<User> users;

    @Column(name = "subject", nullable = false)
    @Enumerated(value = EnumType.STRING)
    private Subject subject;

    @OneToMany(mappedBy = "course")
    private List<Webinar> webinars;

    @ManyToOne
    @JoinColumn(name = "teacher_id",
//            nullable = false,
            foreignKey = @ForeignKey(name = "FK_TEACHER_INFO_COURSE"))
    private Teacher teacher;

    public Integer getDuration(){
        return (int) Duration.between(startDate, endDate).toDays();
    }
}

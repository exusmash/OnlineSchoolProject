package com.example.onlineschoolproject.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "Users",
        uniqueConstraints = {@UniqueConstraint(name = "uniqePhone", columnNames = "phone"),
                @UniqueConstraint(name = "uniqeLogin", columnNames = "login"),
                @UniqueConstraint(name = "uniqeEmail", columnNames = "email")})
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SequenceGenerator(name = "default_generator", sequenceName = "users_sequence", allocationSize = 1)
public class User extends GenericModel {
    @Column(name = "login", nullable = false)
    private String login;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(name = "middle_name", nullable = false)
    private String middleName;

    @Column(name = "birth_date")
    private LocalDate birthDate;

    @Column(name = "phone", nullable = false)
    private String phone;

    @Column(name = "address")
    private String address;

    @Column(name = "email", nullable = false)
    private String email;

    @ManyToOne
    @JoinColumn(name = "role_id", nullable = false,
            foreignKey = @ForeignKey(name = "FK_ROLE_INFO_USER"))
    private Role role;

    @ManyToMany
    @JoinTable(name = "course_users",
            joinColumns = @JoinColumn(name = "user_id"),
            foreignKey = @ForeignKey(name = "FK_USERS_COURSES"),
            inverseJoinColumns = @JoinColumn(name = "course_id"),
            inverseForeignKey = @ForeignKey(name = "FK_COURSES_USERS")
    )
    private List<Course> courses;

    @ManyToMany
    @JoinTable(name = "webinar_users",
            joinColumns = @JoinColumn(name = "user_id"),
            foreignKey = @ForeignKey(name = "FK_USERS_WEBINARS"),
            inverseJoinColumns = @JoinColumn(name = "webinar_id"),
            inverseForeignKey = @ForeignKey(name = "FK_WEBINARS_USERS"))
    private List<Webinar> webinars;
}

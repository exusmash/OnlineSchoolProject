package com.example.onlineschoolproject.dto;

import com.example.onlineschoolproject.model.Subject;
import lombok.*;

import java.util.List;

@ToString
@Getter
@Setter
@NoArgsConstructor
public class TeacherDTO extends GenericDTO{
    private String teacherFIO;
    private String description;
    private List<Long> courseIds;
    private Subject subject;
    private List<Long> webinarIds;
}

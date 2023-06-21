package com.example.onlineschoolproject.dto;

import com.example.onlineschoolproject.model.Subject;
import jakarta.persistence.Column;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

@ToString
@Getter
@Setter
@NoArgsConstructor
public class CourseDTO extends GenericDTO {
    private String name;
    private String description;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private Integer duration;
    private Integer price;
    private List<Long> userIds;
    private Subject subject;
    private List<Long> webinarIds;
    private Long teacherId;

    public Integer getDuration(){
        return (int) Duration.between(startDate, endDate).toDays();
    }
}

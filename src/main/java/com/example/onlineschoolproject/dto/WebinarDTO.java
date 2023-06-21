package com.example.onlineschoolproject.dto;


import com.example.onlineschoolproject.model.Subject;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.List;

@ToString
@Getter
@Setter
@NoArgsConstructor
public class WebinarDTO extends GenericDTO{
    private String name;
    private Integer length;
    private String description;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private List<Long> userIds;
    private Subject subject;
    private Long courseId;
    private Long teacherId;
}

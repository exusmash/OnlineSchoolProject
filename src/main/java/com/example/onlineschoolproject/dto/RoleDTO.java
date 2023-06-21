package com.example.onlineschoolproject.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class RoleDTO extends GenericDTO{
    private Long id;
    private String title;
    private String description;
}


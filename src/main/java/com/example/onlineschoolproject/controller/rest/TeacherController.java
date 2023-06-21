package com.example.onlineschoolproject.controller.rest;

import com.example.onlineschoolproject.controller.rest.GenericController;
import com.example.onlineschoolproject.dto.TeacherDTO;
import com.example.onlineschoolproject.model.Teacher;
import com.example.onlineschoolproject.service.TeacherService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/teachers") // http://localhost:8080/teachers
@Tag(name = "Преподаватели", description = "Контроллер для работы с преподавателями")
public class TeacherController extends GenericController<Teacher, TeacherDTO> {
    public TeacherController(TeacherService teacherService) {
        super(teacherService);
    }
}

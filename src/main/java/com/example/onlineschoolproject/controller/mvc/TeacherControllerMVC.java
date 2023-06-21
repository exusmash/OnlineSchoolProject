package com.example.onlineschoolproject.controller.mvc;

import com.example.onlineschoolproject.dto.CourseDTO;
import com.example.onlineschoolproject.dto.TeacherDTO;
import com.example.onlineschoolproject.service.CourseService;
import com.example.onlineschoolproject.service.TeacherService;
import com.example.onlineschoolproject.service.WebinarService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@Controller
@RequestMapping("/teachers")
public class TeacherControllerMVC {
    private final TeacherService teacherService;
    private final WebinarService webinarService;

    public TeacherControllerMVC(TeacherService teacherService, WebinarService webinarService) {
        this.teacherService = teacherService;
        this.webinarService = webinarService;
    }

    @GetMapping
    public String getAll(Model model) {
        List<TeacherDTO> teachers = teacherService.listAll();
        model.addAttribute("teachers", teachers);
        return "teachers/viewAllTeachers";
    }

    @GetMapping("/add")
    public String create() {
        return "teachers/addTeacher";
    }
    @PostMapping("/add")
    public String create(@ModelAttribute("teacherForm") TeacherDTO newTeacher) {
        log.info(newTeacher.toString());
        teacherService.create(newTeacher);
        return "redirect:/teachers";
    }

    @GetMapping("/{id}")
    public String getOne(@PathVariable("id") Long id, Model model) {
        model.addAttribute("teacher", teacherService.getOne(id));
        model.addAttribute("webinars", webinarService.listAll());
        return "teachers/viewOneTeacher";
    }
}

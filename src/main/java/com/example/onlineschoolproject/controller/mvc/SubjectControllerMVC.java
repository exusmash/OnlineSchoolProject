package com.example.onlineschoolproject.controller.mvc;

import com.example.onlineschoolproject.dto.CourseDTO;
import com.example.onlineschoolproject.dto.TeacherDTO;
import com.example.onlineschoolproject.dto.WebinarDTO;
import com.example.onlineschoolproject.model.Subject;
import com.example.onlineschoolproject.service.CourseService;
import com.example.onlineschoolproject.service.TeacherService;
import com.example.onlineschoolproject.service.WebinarService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/subjects")
public class SubjectControllerMVC {
    private final WebinarService webinarService;
    private final CourseService courseService;
    private final TeacherService teacherService;

    public SubjectControllerMVC(WebinarService webinarService, CourseService courseService, TeacherService teacherService) {
        this.webinarService = webinarService;
        this.courseService = courseService;
        this.teacherService = teacherService;
    }


    @GetMapping("/{name}")
    public String getOne(@PathVariable("name") String name, Model model) {
        List<CourseDTO> courses = courseService.getCoursesBySubjectName(name);
        List<TeacherDTO> teachers = teacherService.getTeachersBySubjectName(name);
        List<WebinarDTO> webinars = webinarService.getWebinarsBySubjectName(name);
        model.addAttribute("courses", courses);
        model.addAttribute("webinars", webinars);
        model.addAttribute("teachers", teachers);
        return "subjects/viewOneSubject";
    }
}

package com.example.onlineschoolproject.controller.mvc;

import com.example.onlineschoolproject.dto.CourseDTO;
import com.example.onlineschoolproject.service.CourseService;
import com.example.onlineschoolproject.service.WebinarService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@Controller
@RequestMapping("/courses")
public class CourseControllerMVC {
    private final CourseService courseService;
    private final WebinarService webinarService;

    public CourseControllerMVC(CourseService courseService, WebinarService webinarService) {
        this.courseService = courseService;
        this.webinarService = webinarService;
    }

    @GetMapping
    public String getAll(Model model) {
        List<CourseDTO> courses = courseService.listAll();
        model.addAttribute("courses", courses);
        return "courses/viewAllCourses";
    }

    @GetMapping("/add")
    public String create() {
        return "courses/addCourse";
    }
    @PostMapping("/add")
    public String create(@ModelAttribute("courseForm") CourseDTO newCourse) {
        log.info(newCourse.toString());
        courseService.create(newCourse);
        return "redirect:/courses";
    }
    @GetMapping("/{id}")
    public String getOne(@PathVariable("id") Long id, Model model) {
        model.addAttribute("course", courseService.getOne(id));
        model.addAttribute("webinars", webinarService.listAll());
        return "courses/viewOneCourse";
    }
}

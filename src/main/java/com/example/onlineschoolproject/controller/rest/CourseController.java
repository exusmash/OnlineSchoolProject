package com.example.onlineschoolproject.controller.rest;

import com.example.onlineschoolproject.dto.CourseDTO;
import com.example.onlineschoolproject.model.Course;
import com.example.onlineschoolproject.service.CourseService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/courses") // http://localhost:8080/courses
@Tag(name = "Курсы", description = "Контроллер для работы с курсами")
public class CourseController extends GenericController<Course, CourseDTO> {
    public CourseController(CourseService courseService) {
        super(courseService);
    }

    @Operation(description = "Добавить ученика на курс")
    @RequestMapping(value = "/addStudent", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CourseDTO> addStudent(@RequestParam(value = "userId") Long userId,
                                               @RequestParam(value = "courseId") Long courseId) {
        return ResponseEntity.status(HttpStatus.OK).body(((CourseService) service).addStudent(userId, courseId));
    }

    @Operation(description = "Добавить вебинар к курсу")
    @RequestMapping(value = "/addWebinar", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CourseDTO> addWebinar(@RequestParam(value = "webinarId") Long webinarId,
                                                @RequestParam(value = "courseId") Long courseId) {
        return ResponseEntity.status(HttpStatus.OK).body(((CourseService) service).addWebinar(webinarId, courseId));
    }

    @Operation(description = "Добавить преподавателя на курс")
    @RequestMapping(value = "/addTeacher", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CourseDTO> addTeacher(@RequestParam(value = "teacherId") Long teacherId,
                                                @RequestParam(value = "courseId") Long courseId) {
        return ResponseEntity.status(HttpStatus.OK).body(((CourseService) service).addTeacher(teacherId, courseId));
    }
}

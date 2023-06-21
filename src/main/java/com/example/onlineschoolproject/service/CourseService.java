package com.example.onlineschoolproject.service;

import com.example.onlineschoolproject.dto.CourseDTO;
import com.example.onlineschoolproject.dto.WebinarDTO;
import com.example.onlineschoolproject.mapper.GenericMapper;
import com.example.onlineschoolproject.model.*;
import com.example.onlineschoolproject.repository.*;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import java.util.ArrayList;
import java.util.List;

@Service
public class CourseService extends GenericService<Course, CourseDTO> {
    private final UserRepository userRepository;
    private final WebinarRepository webinarRepository;
    private final TeacherRepository teacherRepository;
    private final CourseRepository courseRepository;


    public CourseService(GenericRepository<Course> repository, GenericMapper<Course, CourseDTO> mapper, UserRepository userRepository, WebinarRepository webinarRepository, TeacherRepository teacherRepository, CourseRepository courseRepository) {
        super(repository, mapper);
        this.userRepository = userRepository;
        this.webinarRepository = webinarRepository;
        this.teacherRepository = teacherRepository;
        this.courseRepository = courseRepository;
    }

    public CourseDTO addStudent(Long userId, Long courseId) {
        CourseDTO courseDTO = getOne(courseId);
        User user = userRepository.findById(userId).orElseThrow(() -> new NotFoundException("пользователь не найден"));
        courseDTO.getUserIds().add(user.getId());
        update(courseDTO);
        return courseDTO;
    }

    public CourseDTO addWebinar(Long webinarId, Long courseId) {
        CourseDTO courseDTO = getOne(courseId);
        Webinar webinar = webinarRepository.findById(webinarId).orElseThrow(() -> new NotFoundException("вебинар не найден"));
        courseDTO.getWebinarIds().add(webinar.getId());
        update(courseDTO);
        return courseDTO;
    }

    public CourseDTO addTeacher(Long teacherId, Long courseId) {
        CourseDTO courseDTO = getOne(courseId);
        Teacher teacher = teacherRepository.findById(teacherId).orElseThrow(() -> new NotFoundException("преподаватель не найден"));
        courseDTO.setTeacherId(teacher.getId());
        update(courseDTO);
        return courseDTO;
    }

    public List<CourseDTO> getCoursesBySubjectName(String name) {
        List<CourseDTO> courses = mapper.toDTOs(courseRepository.findAll());
        List<CourseDTO> resultCourses = new ArrayList<>();
        for (CourseDTO course : courses) {
            if (course.getSubject().getSubjectTextDisplay().equals(name))
                resultCourses.add(course);
        }
        return resultCourses;
    }
}

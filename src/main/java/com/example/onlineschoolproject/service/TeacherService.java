package com.example.onlineschoolproject.service;

import com.example.onlineschoolproject.dto.CourseDTO;
import com.example.onlineschoolproject.dto.TeacherDTO;
import com.example.onlineschoolproject.dto.WebinarDTO;
import com.example.onlineschoolproject.mapper.GenericMapper;
import com.example.onlineschoolproject.model.Course;
import com.example.onlineschoolproject.model.Subject;
import com.example.onlineschoolproject.model.Teacher;
import com.example.onlineschoolproject.repository.GenericRepository;
import com.example.onlineschoolproject.repository.TeacherRepository;
import com.example.onlineschoolproject.repository.UserRepository;
import com.example.onlineschoolproject.repository.WebinarRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TeacherService extends GenericService<Teacher, TeacherDTO> {
    private final TeacherRepository teacherRepository;
    protected TeacherService(GenericRepository<Teacher> repository, GenericMapper<Teacher, TeacherDTO> mapper, TeacherRepository teacherRepository) {
        super(repository, mapper);
        this.teacherRepository = teacherRepository;
    }

    public List<TeacherDTO> getTeachersBySubjectName(String name) {
        List<TeacherDTO> teachers = mapper.toDTOs(teacherRepository.findAll());
        List<TeacherDTO> resultTeachers = new ArrayList<>();
        for (TeacherDTO teacher : teachers) {
            if (teacher.getSubject().getSubjectTextDisplay().equals(name))
                resultTeachers.add(teacher);
        }
        return resultTeachers;
    }
}

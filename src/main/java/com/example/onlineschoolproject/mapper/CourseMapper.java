package com.example.onlineschoolproject.mapper;

import com.example.onlineschoolproject.dto.CourseDTO;
import com.example.onlineschoolproject.model.*;
import com.example.onlineschoolproject.repository.TeacherRepository;
import com.example.onlineschoolproject.repository.UserRepository;
import com.example.onlineschoolproject.repository.WebinarRepository;
import jakarta.annotation.PostConstruct;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import org.webjars.NotFoundException;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Component
public class CourseMapper extends GenericMapper<Course, CourseDTO> {
    private final UserRepository userRepository;
    private final WebinarRepository webinarRepository;
    private final TeacherRepository teacherRepository;

    public CourseMapper(ModelMapper mapper, UserRepository userRepository, WebinarRepository webinarRepository, TeacherRepository teacherRepository) {
        super(Course.class, CourseDTO.class, mapper);
        this.userRepository = userRepository;
        this.webinarRepository = webinarRepository;
        this.teacherRepository = teacherRepository;
    }

    @PostConstruct
    protected void setupMapper() {
        modelMapper
                .createTypeMap(Course.class, CourseDTO.class)
                .addMappings(mapping -> mapping.skip(CourseDTO::setUserIds)).setPostConverter(toDTOConverter())
                .addMappings(mapping -> mapping.skip(CourseDTO::setWebinarIds)).setPostConverter(toDTOConverter())
                .addMappings(mapping -> mapping.skip(CourseDTO::setTeacherId)).setPostConverter(toDTOConverter());

        modelMapper
                .createTypeMap(CourseDTO.class, Course.class)
                .addMappings(mapping -> mapping.skip(Course::setUsers)).setPostConverter(toEntityConverter())
                .addMappings(mapping -> mapping.skip(Course::setWebinars)).setPostConverter(toEntityConverter())
                .addMappings(mapping -> mapping.skip(Course::setTeacher)).setPostConverter(toEntityConverter());
    }


    @Override
    protected void mapSpecificFields(CourseDTO source, Course destination) {
        if (!Objects.isNull(source.getUserIds())) {
            destination.setUsers(userRepository.findAllById(source.getUserIds()));
        } else {
            destination.setUsers(Collections.emptyList());
        }

        if (!Objects.isNull(source.getWebinarIds())) {
            destination.setWebinars(webinarRepository.findAllById(source.getWebinarIds()));
        } else {
            destination.setWebinars(Collections.emptyList());
        }

        if (!Objects.isNull(source.getTeacherId())) {
            destination.setTeacher(teacherRepository.findById(source.getTeacherId()).orElseThrow(() -> new NotFoundException("преподаватель не найден")));
        } else {
            destination.setTeacher(null);
        }
    }

    @Override
    protected void mapSpecificFields(Course source, CourseDTO destination) {
        destination.setUserIds(getIds(source, User.class));
        destination.setWebinarIds(getIds(source, Webinar.class));
        destination.setTeacherId(getId(source, Teacher.class));
    }


    @Override
    protected <T extends GenericModel> List<Long> getIds(Course source, Class<T> clazz) {
        if (clazz == User.class) {
            return Objects.isNull(source) || Objects.isNull(source.getUsers())
                    ? Collections.emptyList()
                    : source.getUsers().stream()
                    .map(GenericModel::getId)
                    .collect(Collectors.toList());
        } else if (clazz == Webinar.class) {
            return Objects.isNull(source) || Objects.isNull(source.getWebinars())
                    ? Collections.emptyList()
                    : source.getWebinars().stream()
                    .map(GenericModel::getId)
                    .collect(Collectors.toList());
        }
        return Collections.emptyList();
    }

    @Override
    protected <T extends GenericModel> Long getId(Course source, Class<T> clazz) {
        if (clazz == Teacher.class) {
            return Objects.isNull(source) || Objects.isNull(source.getTeacher())
                    ? null
                    : source.getTeacher().getId();
        }
        return null;
    }
}

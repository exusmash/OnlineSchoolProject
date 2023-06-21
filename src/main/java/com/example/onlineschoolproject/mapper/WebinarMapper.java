package com.example.onlineschoolproject.mapper;

import com.example.onlineschoolproject.dto.TeacherDTO;
import com.example.onlineschoolproject.dto.UserDTO;
import com.example.onlineschoolproject.dto.WebinarDTO;
import com.example.onlineschoolproject.model.*;
import com.example.onlineschoolproject.repository.CourseRepository;
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
public class WebinarMapper extends GenericMapper<Webinar, WebinarDTO> {
    private final UserRepository userRepository;
    private final CourseRepository courseRepository;
    private final TeacherRepository teacherRepository;

    public WebinarMapper(ModelMapper mapper, CourseRepository courseRepository, UserRepository userRepository, TeacherRepository teacherRepository) {
        super(Webinar.class, WebinarDTO.class, mapper);
        this.courseRepository = courseRepository;
        this.userRepository = userRepository;
        this.teacherRepository = teacherRepository;
    }

    @PostConstruct
    protected void setupMapper() {
        modelMapper.
                createTypeMap(Webinar.class, WebinarDTO.class)
                .addMappings(mapping -> mapping.skip(WebinarDTO::setCourseId)).setPostConverter(toDTOConverter())
                .addMappings(mapping -> mapping.skip(WebinarDTO::setTeacherId)).setPostConverter(toDTOConverter())
                .addMappings(mapping -> mapping.skip(WebinarDTO::setUserIds)).setPostConverter(toDTOConverter());
        modelMapper.
                createTypeMap(WebinarDTO.class, Webinar.class)
                .addMappings(mapping -> mapping.skip(Webinar::setTeacher)).setPostConverter(toEntityConverter())
                .addMappings(mapping -> mapping.skip(Webinar::setUsers)).setPostConverter(toEntityConverter())
                .addMappings(mapping -> mapping.skip(Webinar::setCourse)).setPostConverter(toEntityConverter());
    }

    @Override
    protected void mapSpecificFields(WebinarDTO source, Webinar destination) {
        if (!Objects.isNull(source.getUserIds())) {
            destination.setUsers(userRepository.findAllById(source.getUserIds()));
        } else {
            destination.setUsers(Collections.emptyList());
        }

        if (!Objects.isNull(source.getCourseId())) {
            destination.setCourse(courseRepository.findById(source.getCourseId()).orElseThrow(() -> new NotFoundException("преподаватель не найден")));
        } else {
            destination.setCourse(null);
        }

        if (!Objects.isNull(source.getTeacherId())) {
            destination.setTeacher(teacherRepository.findById(source.getTeacherId()).orElseThrow(() -> new NotFoundException("преподаватель не найден")));
        } else {
            destination.setTeacher(null);
        }
    }

    @Override
    protected void mapSpecificFields(Webinar source, WebinarDTO destination) {
        destination.setCourseId(getId(source, Course.class));
        destination.setTeacherId(getId(source, Teacher.class));
        destination.setUserIds(getIds(source, User.class));
    }


    @Override
    protected <T extends GenericModel> List<Long> getIds(Webinar source, Class<T> clazz) {
        if (clazz == User.class) {
            return Objects.isNull(source) || Objects.isNull(source.getUsers())
                    ? Collections.emptyList()
                    : source.getUsers().stream()
                    .map(GenericModel::getId)
                    .collect(Collectors.toList());
        }
        return Collections.emptyList();
    }

    @Override
    protected <T extends GenericModel> Long getId(Webinar source, Class<T> clazz) {
        if (clazz == Course.class) {
            return Objects.isNull(source) || Objects.isNull(source.getCourse())
                    ? null
                    : source.getCourse().getId();
        } else if (clazz == Teacher.class) {
            return Objects.isNull(source) || Objects.isNull(source.getTeacher())
                    ? null
                    : source.getTeacher().getId();
        }
        return null;
    }
}

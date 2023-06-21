package com.example.onlineschoolproject.mapper;


import com.example.onlineschoolproject.dto.CourseDTO;
import com.example.onlineschoolproject.dto.TeacherDTO;
import com.example.onlineschoolproject.model.*;
import com.example.onlineschoolproject.repository.CourseRepository;
import com.example.onlineschoolproject.repository.WebinarRepository;
import jakarta.annotation.PostConstruct;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
@Component
public class TeacherMapper extends GenericMapper<Teacher, TeacherDTO> {
    private final CourseRepository courseRepository;
    private final WebinarRepository webinarRepository;

    public TeacherMapper(ModelMapper mapper, CourseRepository courseRepository, WebinarRepository webinarRepository) {
        super(Teacher.class, TeacherDTO.class, mapper);
        this.courseRepository = courseRepository;
        this.webinarRepository = webinarRepository;
    }

    @PostConstruct
    protected void setupMapper() {
        modelMapper.
                createTypeMap(Teacher.class, TeacherDTO.class)
                .addMappings(mapping -> mapping.skip(TeacherDTO::setCourseIds)).setPostConverter(toDTOConverter())
                .addMappings(mapping -> mapping.skip(TeacherDTO::setWebinarIds)).setPostConverter(toDTOConverter());
        modelMapper.
                createTypeMap(TeacherDTO.class, Teacher.class)
                .addMappings(mapping -> mapping.skip(Teacher::setCourses)).setPostConverter(toEntityConverter())
                .addMappings(mapping -> mapping.skip(Teacher::setWebinars)).setPostConverter(toEntityConverter());
    }

    @Override
    protected void mapSpecificFields(TeacherDTO source, Teacher destination) {
        if (!Objects.isNull(source.getCourseIds())) {
            destination.setCourses(courseRepository.findAllById(source.getCourseIds()));
        } else {
            destination.setCourses(Collections.emptyList());
        }

        if (!Objects.isNull(source.getWebinarIds())) {
            destination.setWebinars(webinarRepository.findAllById(source.getWebinarIds()));
        } else {
            destination.setWebinars(Collections.emptyList());
        }
    }

    @Override
    protected void mapSpecificFields(Teacher source, TeacherDTO destination) {
        destination.setCourseIds(getIds(source, Course.class));
        destination.setWebinarIds(getIds(source, Webinar.class));
    }


    @Override
    protected <T extends GenericModel> List<Long> getIds(Teacher source, Class<T> clazz) {
        if (clazz == Course.class) {
            return Objects.isNull(source) || Objects.isNull(source.getCourses())
                    ? Collections.emptyList()
                    : source.getCourses().stream()
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
    protected <T extends GenericModel> Long getId(Teacher entity, Class<T> clazz) {
        return null;
    }
}

package com.example.onlineschoolproject.mapper;



import com.example.onlineschoolproject.dto.UserDTO;
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
public class UserMapper extends GenericMapper<User, UserDTO> {
    private final CourseRepository courseRepository;
    private final WebinarRepository webinarRepository;

    public UserMapper(ModelMapper mapper, CourseRepository courseRepository, WebinarRepository webinarRepository) {
        super(User.class, UserDTO.class, mapper);
        this.courseRepository = courseRepository;
        this.webinarRepository = webinarRepository;
    }

    @PostConstruct
    protected void setupMapper() {
        modelMapper.
                createTypeMap(User.class, UserDTO.class)
                .addMappings(mapping -> mapping.skip(UserDTO::setCourseIds)).setPostConverter(toDTOConverter())
                .addMappings(mapping -> mapping.skip(UserDTO::setWebinarIds)).setPostConverter(toDTOConverter());
        modelMapper.
                createTypeMap(UserDTO.class, User.class)
                .addMappings(mapping -> mapping.skip(User::setCourses)).setPostConverter(toEntityConverter())
                .addMappings(mapping -> mapping.skip(User::setWebinars)).setPostConverter(toEntityConverter());
    }

    @Override
    protected void mapSpecificFields(UserDTO source, User destination) {
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
    protected void mapSpecificFields(User source, UserDTO destination) {
        destination.setCourseIds(getIds(source, Course.class));
        destination.setWebinarIds(getIds(source, Webinar.class));
    }


    @Override
    protected <T extends GenericModel> List<Long> getIds(User source, Class<T> clazz) {
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
    protected <T extends GenericModel> Long getId(User entity, Class<T> clazz) {
        return null;
    }

}

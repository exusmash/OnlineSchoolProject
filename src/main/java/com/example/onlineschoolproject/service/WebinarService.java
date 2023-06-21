package com.example.onlineschoolproject.service;

import com.example.onlineschoolproject.dto.TeacherDTO;
import com.example.onlineschoolproject.dto.WebinarDTO;
import com.example.onlineschoolproject.mapper.GenericMapper;
import com.example.onlineschoolproject.model.Subject;
import com.example.onlineschoolproject.model.Teacher;
import com.example.onlineschoolproject.model.Webinar;
import com.example.onlineschoolproject.repository.GenericRepository;
import com.example.onlineschoolproject.repository.WebinarRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class WebinarService extends GenericService<Webinar, WebinarDTO> {
    private final WebinarRepository webinarRepository;
    protected WebinarService(GenericRepository<Webinar> repository, GenericMapper<Webinar, WebinarDTO> mapper, WebinarRepository webinarRepository) {
        super(repository, mapper);
        this.webinarRepository = webinarRepository;
    }

    public List<WebinarDTO> getWebinarsBySubjectName(String name) {
        List<WebinarDTO> webinars = mapper.toDTOs(webinarRepository.findAll());
        List<WebinarDTO> resultWebinars = new ArrayList<>();
        for (WebinarDTO webinar : webinars) {
            if (webinar.getSubject().getSubjectTextDisplay().equals(name))
                resultWebinars.add(webinar);
        }
        return resultWebinars;
    }
}

package com.example.onlineschoolproject.controller.rest;

import com.example.onlineschoolproject.controller.rest.GenericController;
import com.example.onlineschoolproject.dto.WebinarDTO;
import com.example.onlineschoolproject.model.Webinar;
import com.example.onlineschoolproject.service.WebinarService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/webinars") // http://localhost:8080/webinars
@Tag(name = "Вебинары", description = "Контроллер для работы с вебинарами")
public class WebinarController extends GenericController<Webinar, WebinarDTO> {
    public WebinarController(WebinarService webinarService) {
        super(webinarService);
    }
}

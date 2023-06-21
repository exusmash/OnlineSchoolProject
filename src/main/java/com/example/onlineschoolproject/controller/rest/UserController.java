package com.example.onlineschoolproject.controller.rest;

import com.example.onlineschoolproject.controller.rest.GenericController;
import com.example.onlineschoolproject.dto.UserDTO;
import com.example.onlineschoolproject.model.User;
import com.example.onlineschoolproject.service.UserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
@Tag(name = "Пользователи",
        description = "Контроллер для работы с пользователями онлайн школы")
public class UserController extends GenericController<User, UserDTO> {
    public UserController(UserService userService) {
        super(userService);
    }
}

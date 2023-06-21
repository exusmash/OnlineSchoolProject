package com.example.onlineschoolproject.service;

import com.example.onlineschoolproject.dto.RoleDTO;
import com.example.onlineschoolproject.dto.UserDTO;
import com.example.onlineschoolproject.model.User;
import com.example.onlineschoolproject.repository.GenericRepository;
import org.springframework.stereotype.Service;
import com.example.onlineschoolproject.mapper.GenericMapper;

@Service
public class UserService extends GenericService<User, UserDTO> {
    public UserService(GenericRepository<User> repository, GenericMapper<User, UserDTO> mapper) {
        super(repository, mapper);
    }

    @Override
    public UserDTO create(UserDTO newObject) {
        RoleDTO roleDTO = new RoleDTO();
        roleDTO.setId(1L);
        newObject.setRole(roleDTO);
        return mapper.toDTO(repository.save(mapper.toEntity(newObject)));
    }
}

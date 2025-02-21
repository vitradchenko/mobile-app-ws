package com.aura.app.ws.ui.service;

import com.aura.app.ws.io.entity.UserEntity;
import com.aura.app.ws.io.repository.UserRepository;
import com.aura.app.ws.shared.dto.UserDto;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    public UserDto createUser(UserDto userDto) {
        if (userRepository.findUserByEmail(userDto.getEmail()) != null) {
            throw new RuntimeException("User is already exist");
        }

        UserEntity entity = new UserEntity();
        BeanUtils.copyProperties(userDto, entity);

        entity.setEncryptedPassword("test");
        entity.setUserId(UUID.randomUUID().toString());

        var saved = userRepository.save(entity);
        var returnValue = new UserDto();
        BeanUtils.copyProperties(saved, returnValue);
        return returnValue;
    }
}

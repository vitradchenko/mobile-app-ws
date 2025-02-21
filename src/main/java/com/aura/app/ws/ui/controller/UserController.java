package com.aura.app.ws.ui.controller;

import com.aura.app.ws.shared.dto.UserDto;
import com.aura.app.ws.ui.model.request.UserDetailRequestModel;
import com.aura.app.ws.ui.model.response.UserRest;
import com.aura.app.ws.ui.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("users")
public class UserController {
    @Autowired
    public UserService userService;


    @GetMapping
    @ResponseBody
    public String getUser() {
        return "Hello world!";
    }

    @PostMapping
    public UserRest createUser(@RequestBody UserDetailRequestModel model) {
        var dto = new UserDto();
        BeanUtils.copyProperties(model, dto);
        var createdUser = userService.createUser(dto);

        var returnValue = new UserRest();
        BeanUtils.copyProperties(createdUser, returnValue);
        return returnValue;
    }
}

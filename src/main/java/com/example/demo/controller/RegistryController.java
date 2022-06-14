package com.example.demo.controller;

import com.example.demo.dto.QuestionnaireDto;
import com.example.demo.dto.UserDto;
import com.example.demo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class RegistryController {

    private final UserService userService;

    @PostMapping("/register")
    QuestionnaireDto register(@RequestBody UserDto dto) {
        return userService.saveUser(dto);
    }
}

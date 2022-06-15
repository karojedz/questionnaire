package com.example.demo.service;

import com.example.demo.dto.QuestionnaireDto;
import com.example.demo.dto.UserDto;
import com.example.demo.mapper.UserMapper;
import com.example.demo.model.Questionnaire;
import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final QuestionnaireService questionnaireService;

    public QuestionnaireDto saveUser(UserDto dto) {
        User user = userMapper.toUser(dto);
        User saved = userRepository.save(user);
        return questionnaireService.createQuestionnaire(saved);
    }

    public QuestionnaireDto getQuestionnaire(Long userId) {
        return questionnaireService.getQuestionnaireByUserId(userId);
    }
}

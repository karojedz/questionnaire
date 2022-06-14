package com.example.demo.controller;

import com.example.demo.dto.QuestionnaireDto;
import com.example.demo.service.QuestionnaireService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AnswersController {

    private final QuestionnaireService questionnaireService;

    @PostMapping("/answers")
        // Just copy the response you got during registration
        // and exchange text of the questions for answers.
    ResponseEntity<String> answer(@RequestBody QuestionnaireDto questionnaireDto) {
        return ResponseEntity.ok(questionnaireService.saveAnswers(questionnaireDto));
    }
}

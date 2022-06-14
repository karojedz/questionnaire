package com.example.demo.dto;

import com.example.demo.model.Question;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
public class QuestionnaireDto {

    private Long id;

    private Map<Long, String> randomQuestions = new HashMap<>();

    public String addQuestion(Question question) {
        return randomQuestions.put(question.getId(), question.getActualQuestion());
    }
}

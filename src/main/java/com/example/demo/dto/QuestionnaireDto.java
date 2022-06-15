package com.example.demo.dto;

import com.example.demo.model.Question;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class QuestionnaireDto {

    private Long id;

    private Map<Long, String> randomQuestions = new HashMap<>();

    private Map<String, String> questionsWithAnswers = new HashMap<>();

    public String addQuestion(Question question) {
        return randomQuestions.put(question.getId(), question.getActualQuestion());
    }

    public String addQuestionWithAnswer(String question, String answer) {
        return questionsWithAnswers.put(question, answer);
    }
}

package com.example.demo.service;

import com.example.demo.model.Question;
import com.example.demo.repository.QuestionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class QuestionService {

    private final QuestionRepository questionRepository;
    private Long maximumQuestionId;

    public Question drawQuestion() throws IllegalArgumentException {
        if (maximumQuestionId == null) maximumQuestionId = questionRepository.getMaximumId();
        Random random = new Random();
        Long randomId = Math.abs(random.nextLong()) % maximumQuestionId + 1;
        Optional<Question> question = questionRepository.findById(randomId);
        if (question.isPresent()) return question.get();
        else throw new IllegalArgumentException("There is a problem with getting a question out of repository.");
    }
}

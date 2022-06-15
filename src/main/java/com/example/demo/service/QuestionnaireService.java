package com.example.demo.service;

import com.example.demo.dto.QuestionnaireDto;
import com.example.demo.mapper.UserMapper;
import com.example.demo.model.Question;
import com.example.demo.model.Questionnaire;
import com.example.demo.model.User;
import com.example.demo.repository.QuestionnaireRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class QuestionnaireService {

    private final QuestionnaireRepository questionnaireRepository;
    private final QuestionService questionService;
    private final UserMapper userMapper;

    public QuestionnaireDto createQuestionnaire(User user) {
        Questionnaire questionnaire = new Questionnaire();
        QuestionnaireDto questionnaireDto = new QuestionnaireDto();
        while (questionnaire.getRandomQuestions().size() < 5) {
            Question question = questionService.drawQuestion();
            questionnaire.addQuestion(question);        //adds reference to the question
            questionnaireDto.addQuestion(question);     //adds id as key and text of the question as value
        }
        questionnaire.setUser(user);
        Questionnaire saved = questionnaireRepository.save(questionnaire);
        questionnaireDto.setId(saved.getId());
        return questionnaireDto;
    }

    public String saveAnswers(QuestionnaireDto questionnaireDto) {
        Long id = questionnaireDto.getId();
        Optional<Questionnaire> foundQuestionnaire = questionnaireRepository.findById(id);
        if (foundQuestionnaire.isPresent()) {
            Questionnaire questionnaire = foundQuestionnaire.get();
            Map<Long, String> randomQuestionsAnswered = questionnaireDto.getRandomQuestions();
            for (long key : randomQuestionsAnswered.keySet()) {
                questionnaire.getRandomQuestions().put(key, randomQuestionsAnswered.get(key));
            }
            questionnaireRepository.save(questionnaire);
            return "Your answers were saved.";
        } else {
            throw new IllegalArgumentException("We cannot find a questionnaire you are trying to answer");
        }
    }

    public QuestionnaireDto getQuestionnaireByUserId(Long userId) {
        Optional<Questionnaire> possiblyQuestionnaire = questionnaireRepository.findByUserId(userId);
        Questionnaire questionnaire;
        if (possiblyQuestionnaire.isPresent()) questionnaire = possiblyQuestionnaire.get();
        else throw new IllegalArgumentException("Couldn't find a user with given id");
        Map<Long, String> randomQuestions = questionnaire.getRandomQuestions();
        QuestionnaireDto questionnaireDto = new QuestionnaireDto();
        for (long key: randomQuestions.keySet()) {
            String question = questionService.getQuestionTextWithId(key);
            String answer = randomQuestions.get(key);
            questionnaireDto.addQuestionWithAnswer(question, answer);
        }
        return questionnaireDto;
    }
}

package com.example.demo.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Questionnaire {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ElementCollection
    private Map<Long, String> randomQuestions = new HashMap<>();

    @OneToOne
    private User user;

    public String addQuestion(Question question) {
        return randomQuestions.put(question.getId(), "");
    }
}

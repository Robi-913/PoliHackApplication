package com.example.PoliHack.controller;


import com.example.PoliHack.model.Quiz;
import com.example.PoliHack.model.Response;
import com.example.PoliHack.repository.QuizRepository;
import com.example.PoliHack.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/quiz")
public class QuizController {
    @Autowired
    private QuizRepository quizRepository;
    private final QuizService quizService;


    public QuizController(QuizService quizService) {
        this.quizService = quizService;
    }

    // Fetch all quizzes
    @GetMapping()
    public List<Object> getAllQuizzes() {
        List<Quiz> quizzes = quizRepository.findAll();  // Fetch all quizzes from the database

        return quizzes.stream()
                .map(quiz -> new Object() {
                    public final String question = quiz.getQuestion();  // Map the question
                    public final List<String> options = quiz.getOptions();  // Map the options
                })
                .collect(Collectors.toList());
    }

    @PostMapping()
    public String addSubmit(@RequestBody List<String> options)
    {
        List<Quiz> quizzes = quizRepository.findAll();
        String aiInput=quizService.fetchResponses(options,quizzes);

        RestTemplate restTemplate = new RestTemplate();
        String aiEndpoint = "http://localhost:8080/ai";  // Replace with the actual AI service URL
        String aiResponse=restTemplate.postForObject(aiEndpoint, aiInput, String.class);

        return aiResponse;

    }

}

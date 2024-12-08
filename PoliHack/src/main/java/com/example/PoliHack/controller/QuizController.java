package com.example.PoliHack.controller;


import com.example.PoliHack.model.Quiz;
import com.example.PoliHack.model.Response;
import com.example.PoliHack.repository.QuizRepository;
import com.example.PoliHack.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<String> addSubmit(@RequestBody List<String> options)
    {
        List<Quiz> quizzes = quizRepository.findAll();
        String aiInput=quizService.fetchResponses(options,quizzes);
        return ResponseEntity.ok(aiInput);
    }

}

package com.example.PoliHack.controller;


import com.example.PoliHack.model.Quiz;
import com.example.PoliHack.model.Response;
import com.example.PoliHack.repository.QuizRepository;
import com.example.PoliHack.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public ResponseEntity<List<Quiz>> getAllQuizzes() {
        List<Quiz> quizzes = quizRepository.findAll();
        return ResponseEntity.ok(quizzes);
    }

    @PostMapping()
    public ResponseEntity<String> addSubmit(@RequestBody List<String> options)
    {
        List<Quiz> quizzes = quizRepository.findAll();
        String aiInput=quizService.fetchResponses(options,quizzes);
        return ResponseEntity.ok(aiInput);
    }

}

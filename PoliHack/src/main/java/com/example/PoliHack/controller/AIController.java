package com.example.PoliHack.controller;


import com.example.PoliHack.web.ApiClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AIController {

    @PostMapping ("/ai")
    public String testAI(String question)
    {
        ApiClient apiClient=new ApiClient();
        String apiResponse=apiClient.sendQuestion(question);
        return apiResponse;

    }
}

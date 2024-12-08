package com.example.PoliHack.controller;


import com.example.PoliHack.web.ApiClient;
import com.example.PoliHack.web.ApiResponse;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AIController {

    @PostMapping ("/ai")
    public void testAI(String question)
    {
        ApiClient apiClient=new ApiClient();
        String apiResponse=apiClient.sendQuestion(question);
//        System.out.println(apiResponse);

    }
}

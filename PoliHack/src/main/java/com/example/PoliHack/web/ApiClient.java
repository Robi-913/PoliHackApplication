package com.example.PoliHack.web;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.http.MediaType;

public class ApiClient {

    private final WebClient webClient;

    public ApiClient() {
        this.webClient = WebClient.builder()
                .baseUrl("http://localhost:8787")
                .build();
    }


    public String sendQuestion(String question) {
        String apiResponse= webClient.post()
                .uri("/api/v1/ai/question")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue("{\"question\": \"" + question + "\"}")
                .retrieve()
                .bodyToMono(String.class) // Transform the response to ApiResponse object
                .block(); // Blocking call for simplicity; avoid in reactive applications
        System.out.println(apiResponse);
        try {
            // Create ObjectMapper for JSON parsing
            ObjectMapper objectMapper = new ObjectMapper();

            // Step 3: Deserialize the outer JSON (e.g., {"response": "..."})
            JsonNode outerJson = objectMapper.readTree(apiResponse);

            // Step 4: Extract the "response" field, which is another JSON string
            String innerJsonString = outerJson.get("response").asText();

            // Step 5: Parse the inner JSON string (e.g., {"message": "..."})
            JsonNode innerJson = objectMapper.readTree(innerJsonString);

            // Step 6: Extract the "message" field from the inner JSON
            String message = innerJson.get("message").asText();
            System.out.println(message);

            // Return or process the extracted message
            return message;

        } catch (Exception e) {
            e.printStackTrace();
            return "Error parsing response";
        }
    }
}


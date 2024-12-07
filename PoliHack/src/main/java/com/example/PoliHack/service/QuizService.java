package com.example.PoliHack.service;


import com.example.PoliHack.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class QuizService {

    public Integer calculateOccurence(List<Integer> ans)
    {
        Map<Integer, Integer> frequencyMap = new HashMap<>();
        for (Integer answer : ans) {
            frequencyMap.put(answer, frequencyMap.getOrDefault(answer, 0) + 1);
        }

        // Step 2: Find the most frequent value
        Integer mostFrequentAnswer = null;
        int maxCount = 0;
        for (Map.Entry<Integer, Integer> entry : frequencyMap.entrySet()) {
            if (entry.getValue() > maxCount) {
                mostFrequentAnswer = entry.getKey();
                maxCount = entry.getValue();
            }
        }
        return maxCount;
    }
}

package com.example.PoliHack.controller;

import com.example.PoliHack.model.LeaderBoard;
import com.example.PoliHack.service.LeaderBoardService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/leaderboard")
public class LeaderBoardController {

    private final LeaderBoardService leaderBoardService;

    public LeaderBoardController(LeaderBoardService leaderBoardService) {
        this.leaderBoardService = leaderBoardService;
    }

    // Endpoint pentru a obține LeaderBoard-ul complet
    @GetMapping
    public List<LeaderBoard> getLeaderBoard() {
        return leaderBoardService.getLeaderBoard();
    }

    // Endpoint pentru a actualiza scorul utilizatorului curent pe baza taskurilor completate
    @PostMapping("/update")
    public LeaderBoard updateScore(@RequestBody List<Integer> taskStatus) {
        if (taskStatus.size() > 31) {
            throw new IllegalArgumentException("Lista trebuie să conțină 30 sau 31 de zile.");
        }
        return leaderBoardService.updateCurrentUserScore(taskStatus);
    }
}

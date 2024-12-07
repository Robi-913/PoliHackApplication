package com.example.PoliHack.controller;

import com.example.PoliHack.model.HabitSyncRequest;
import com.example.PoliHack.service.LeaderBoardService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping("/api/leaderboard")
public class LeaderBoardController {

    private final LeaderBoardService leaderBoardService;

    public LeaderBoardController(LeaderBoardService leaderBoardService) {
        this.leaderBoardService = leaderBoardService;
    }

    /**
     * Returnează LeaderBoard-ul actualizat din baza de date.
     */
    @GetMapping
    public List<Object> getLeaderBoard() {
        return leaderBoardService.getUpdatedLeaderBoard().stream()
                .map(leaderBoard -> new Object() {
                    public final String userId = leaderBoard.getUser().getId();
                    public final String nickname = leaderBoard.getUser().getNickname();
                    public final int score = leaderBoard.getScore();
                    public final int status = leaderBoard.getStatus().getValue();
                    public final boolean isCurrentUser = leaderBoard.isIscurentuser();
                })
                .collect(Collectors.toList());
    }

    /**
     * Procesează vectorul primit și actualizează scorurile.
     */
    @PostMapping("/sync")
    public ResponseEntity<String> syncHabits(@RequestBody HabitSyncRequest request) {
        return leaderBoardService.updateScoresFromSync(request);
    }
}

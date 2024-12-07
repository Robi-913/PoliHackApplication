package com.example.PoliHack.controller;

import com.example.PoliHack.model.HabitSyncRequest;
import com.example.PoliHack.model.LeaderBoard;
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

    @GetMapping
    public List<Object> getLeaderBoard() {
        return leaderBoardService.getLeaderBoard().stream()
                .map(leaderBoard -> new Object() {
                    public final String userId = leaderBoard.getUser().getId();
                    public final String nickname = leaderBoard.getUser().getNickname();
                    public final int score = leaderBoard.getScore();
                    public final int status = leaderBoard.getStatus().getValue();
                    public final boolean isCurrentUser = leaderBoard.isIscurentuser();
                })
                .collect(Collectors.toList());
    }

    @PostMapping
    public List<Object> generateLeaderBoard() {
        return leaderBoardService.generateAndSaveLeaderBoard().stream()
                .map(leaderBoard -> new Object() {
                    public final String userId = leaderBoard.getUser().getId();
                    public final String nickname = leaderBoard.getUser().getNickname();
                    public final int score = leaderBoard.getScore();
                    public final int status = leaderBoard.getStatus().getValue();
                    public final boolean isCurrentUser = leaderBoard.isIscurentuser();
                })
                .collect(Collectors.toList());
    }

    @PostMapping("/score")
    public Object processScore(@RequestBody List<Integer> scores) {
        LeaderBoard updatedLeaderBoard = leaderBoardService.processAndUpdateCurrentUserScore(scores);

        return new Object() {
            public final String userId = updatedLeaderBoard.getUser().getId();
            public final String nickname = updatedLeaderBoard.getUser().getNickname();
            public final int score = updatedLeaderBoard.getScore();
            public final int status = updatedLeaderBoard.getStatus().getValue();
            public final boolean isCurrentUser = updatedLeaderBoard.isIscurentuser();
        };
    }

    @PostMapping("/sync")
    public ResponseEntity<String> syncHabits(@RequestBody HabitSyncRequest request) {
        System.out.println("Received sync request:");
        System.out.println("Year: " + request.getYear());
        System.out.println("Month: " + request.getMonth());
        System.out.println("State Vector: " + request.getStateVector());

        // Here, you would process the request or update the database as needed
        return ResponseEntity.ok("Sync successful");
    }
}

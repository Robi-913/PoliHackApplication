package com.example.PoliHack.controller;

import com.example.PoliHack.service.LeaderBoardService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/leaderboard")
public class LeaderBoardController {

    private final LeaderBoardService leaderBoardService;

    public LeaderBoardController(LeaderBoardService leaderBoardService) {
        this.leaderBoardService = leaderBoardService;
    }

    @GetMapping
    public List<Object> getLeaderBoard() {
        return leaderBoardService.generateLeaderBoard().stream()
                .map(leaderBoard -> {
                    return new Object() {
                        public final String userId = leaderBoard.getUser().getId();
                        public final String nickname = leaderBoard.getUser().getNickname();
                        public final int score = leaderBoard.getScore();
                        public final int status = leaderBoard.getStatus().getValue();
                        public final boolean isCurrentUser = leaderBoard.isIscurentuser();
                    };
                })
                .collect(Collectors.toList());
    }

}

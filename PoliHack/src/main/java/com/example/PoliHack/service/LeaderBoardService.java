package com.example.PoliHack.service;

import com.example.PoliHack.model.LeaderBoard;
import com.example.PoliHack.model.user.User;
import com.example.PoliHack.model.user.UserStatus;
import com.example.PoliHack.model.user.utils.UserSession;
import com.example.PoliHack.repository.LeaderBoardRepository;
import com.example.PoliHack.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
@AllArgsConstructor
public class LeaderBoardService {

    private final UserRepository userRepository;
    private final LeaderBoardRepository leaderBoardRepository;


    public List<LeaderBoard> getLeaderBoard() {
        return leaderBoardRepository.findAll();
    }

    public LeaderBoard processAndUpdateCurrentUserScore(List<Integer> scores) {
        UserSession currentUserSession = UserSession.getInstance();
        String currentUserId = currentUserSession.getUserId();

        if (currentUserId == null || currentUserId.isEmpty()) {
            throw new IllegalArgumentException("Nu există sesiune activă pentru utilizatorul curent.");
        }

        try {
            LeaderBoard leaderBoard = leaderBoardRepository.findByUserId(currentUserId)
                    .orElseThrow(() -> new IllegalArgumentException("Utilizatorul curent nu există în LeaderBoard."));

            int doneCount = (int) scores.stream().filter(score -> score == 2).count();
            int predefinedValue = 10;
            int additionalScore = doneCount * predefinedValue;

            int newScore = leaderBoard.getScore() + additionalScore;
            leaderBoard.setScore(newScore);

            return leaderBoardRepository.save(leaderBoard);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Eroare la procesarea scorului: " + e.getMessage());
        }
    }

}

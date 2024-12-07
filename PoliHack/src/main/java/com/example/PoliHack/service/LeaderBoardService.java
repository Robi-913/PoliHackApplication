package com.example.PoliHack.service;

import com.example.PoliHack.model.LeaderBoard;
import com.example.PoliHack.model.user.User;
import com.example.PoliHack.model.user.UserStatus;
import com.example.PoliHack.model.user.utils.UserSession;
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

    public List<LeaderBoard> generateLeaderBoard() {
        List<User> users = userRepository.findAll(); // Obține toți utilizatorii
        UserSession currentUserSession = UserSession.getInstance();

        List<LeaderBoard> leaderBoardList = new ArrayList<>();

        for (User user : users) {
            // Atribuie valori de test pentru scor și status
            int score = calculateScoreForUser(user); // Funcție care calculează scorul
            int statusValue = determineStatusValueForUser(user); // Funcție care determină valoarea statusului

            // Creează un obiect LeaderBoard
            LeaderBoard leaderBoard = new LeaderBoard();
            leaderBoard.setUser(user);
            leaderBoard.setScore(score);
            leaderBoard.setStatus(UserStatus.fromValue(statusValue));
            leaderBoard.setIscurentuser(user.getId().equals(currentUserSession.getUserId()));

            leaderBoardList.add(leaderBoard);
        }

        // Sortează lista descrescător după scor
        leaderBoardList.sort(Comparator.comparingInt(LeaderBoard::getScore).reversed());

        return leaderBoardList;
    }

    private int calculateScoreForUser(User user) {
        return (int) (Math.random() * 100); // Exemplu: scor random între 0 și 100
    }

    private int determineStatusValueForUser(User user) {
        return (int) (Math.random() * 4); // Exemplu: valoare random între 0 și 3
    }

}

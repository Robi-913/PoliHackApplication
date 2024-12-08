package com.example.PoliHack.service;

import com.example.PoliHack.model.LeaderBoard;
import com.example.PoliHack.model.user.utils.UserSession;
import com.example.PoliHack.repository.LeaderBoardRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class LeaderBoardService {

    private final LeaderBoardRepository leaderBoardRepository;

    // Listează toți utilizatorii din LeaderBoard
    public List<LeaderBoard> getLeaderBoard() {
        return leaderBoardRepository.findAll()
                .stream()
                .sorted((a, b) -> Integer.compare(b.getScore(), a.getScore())) // Sortează după scor descrescător
                .collect(Collectors.toList());
    }

    // Actualizează LeaderBoard pentru utilizatorul curent
    public LeaderBoard updateCurrentUserScore(List<Integer> taskStatus) {
        UserSession currentUserSession = UserSession.getInstance();
        String currentUserId = currentUserSession.getUserId();

        LeaderBoard leaderBoard = leaderBoardRepository.findByUserId(currentUserId)
                .orElseThrow(() -> new IllegalArgumentException("Utilizatorul curent nu există în LeaderBoard."));

        int doneCount = (int) taskStatus.stream().filter(status -> status == 2).count(); // Numără zilele 'done'
        int scoreIncrement = doneCount * 10; // 10 puncte per zi 'done'

        leaderBoard.setScore(leaderBoard.getScore() + scoreIncrement); // Adaugă scorul
        leaderBoardRepository.save(leaderBoard);

        return leaderBoard;
    }
}

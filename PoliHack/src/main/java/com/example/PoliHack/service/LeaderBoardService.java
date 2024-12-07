package com.example.PoliHack.service;

import com.example.PoliHack.model.HabitSyncRequest;
import com.example.PoliHack.model.LeaderBoard;
import com.example.PoliHack.repository.LeaderBoardRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class LeaderBoardService {

    private final LeaderBoardRepository leaderBoardRepository;

    /**
     * Actualizează scorurile pe baza unui vector de stare din HabitSyncRequest.
     */
    public ResponseEntity<String> updateScoresFromSync(HabitSyncRequest request) {
        List<LeaderBoard> leaderBoardList = leaderBoardRepository.findAll();

        if (leaderBoardList.isEmpty()) {
            return ResponseEntity.badRequest().body("No LeaderBoard entries found.");
        }

        // Procesează fiecare LeaderBoard și actualizează scorurile
        for (LeaderBoard leaderBoard : leaderBoardList) {
            List<Integer> stateVector = request.getStateVector();
            int doneCount = (int) stateVector.stream().filter(state -> state == 2).count();
            int predefinedValue = 10; // Valoare predefinită pentru fiecare 'done'
            int additionalScore = doneCount * predefinedValue;

            // Actualizează scorul
            leaderBoard.setScore(leaderBoard.getScore() + additionalScore);
        }

        // Salvează modificările în baza de date
        leaderBoardRepository.saveAll(leaderBoardList);

        return ResponseEntity.ok("Scores updated successfully based on the sync request.");
    }

    /**
     * Returnează lista actualizată a LeaderBoard-ului.
     */
    public List<LeaderBoard> getUpdatedLeaderBoard() {
        return leaderBoardRepository.findAll();
    }
}

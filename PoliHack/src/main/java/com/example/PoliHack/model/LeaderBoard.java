package com.example.PoliHack.model;

import com.example.PoliHack.model.user.User;
import com.example.PoliHack.model.user.UserStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LeaderBoard {
    private User user;
    private UserStatus status;
    private int score;
    private boolean iscurentuserl;
}

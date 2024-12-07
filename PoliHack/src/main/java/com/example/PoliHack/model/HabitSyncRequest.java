package com.example.PoliHack.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class HabitSyncRequest {
    private int year;
    private int month;
    private List<Integer> stateVector;
}

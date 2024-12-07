package com.example.PoliHack.controller;


import com.example.PoliHack.model.Habit;
import com.example.PoliHack.service.HabitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/habit")
public class HabitController {

    @Autowired
    private final HabitService habitService;


    public HabitController(HabitService habitService)
    {
        this.habitService=habitService;
    }



    @GetMapping("/{habitId}")
    public ResponseEntity<List<Habit>> getHabits(List<Integer> habitsIds)
    {
        return ResponseEntity.ok(habitService.fetchHabitsDb(habitsIds));
    }


}

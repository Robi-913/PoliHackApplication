package com.example.PoliHack.controller;


import com.example.PoliHack.model.Habit;
import com.example.PoliHack.service.HabitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class HabitController {

    @Autowired
    private HabitService habitService;

    @GetMapping
    public List<Habit> choosingHabits()
    {
        return habitService.fetchAll().stream().filter(Habit::isChosen).collect(Collectors.toList());
    }


}

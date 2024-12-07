package com.example.PoliHack.service;


import com.example.PoliHack.model.Habit;
import com.example.PoliHack.repository.HabitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class HabitService {

    @Autowired
    private final HabitRepository habitRepository;

    public HabitService(HabitRepository habitRepository)
    {
        this.habitRepository=habitRepository;
    }

    public List<Habit> fetchHabitsDb(List<Integer> habitIds)
    {
        List<Habit> habits=habitRepository.findAll().stream()
                .filter(x->habitIds.contains(x.getId()))
                .collect(Collectors.toList());
        return habits;
    }


}

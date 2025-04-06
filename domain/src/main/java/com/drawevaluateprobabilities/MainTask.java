package com.drawevaluateprobabilities;

import com.drawevaluateprobabilities.usecases.CalculateProbabilities;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class MainTask implements CommandLineRunner {

    private final CalculateProbabilities service;

    public MainTask(CalculateProbabilities calculateProbabilities) {
        this.service = calculateProbabilities;
    }

    @Override
    public void run(String... args) throws Exception {
        log.info("Iniciando tarea principal...");
        service.executeAll();
        log.info("Tarea completada.");
    }
}


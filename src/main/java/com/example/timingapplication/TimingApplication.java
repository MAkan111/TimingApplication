package com.example.timingapplication;

import com.example.timingapplication.Model.Plant;
import com.example.timingapplication.Service.PlantService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.util.List;

@SpringBootApplication
@RequiredArgsConstructor
public class TimingApplication {
    private final PlantService plantService;

    public static void main(String[] args) {
        SpringApplication.run(TimingApplication.class, args);
    }

    @EventListener(ApplicationReadyEvent.class)
    public void onReady() {
        plantService.addPlants(List.of(new Plant("Роза", "Цветок"),
                                    new Plant("Архидея", "Цветок")));
        plantService.addPlant(new Plant("Арбуз", "Фрукт"));
        plantService.getAllPlants();
        plantService.updatePlant("Арбуз", new Plant("Арбуз", "ягода"));
        plantService.removePlant("Роза");
        plantService.getPlantByName("Арбуз");

    }
}

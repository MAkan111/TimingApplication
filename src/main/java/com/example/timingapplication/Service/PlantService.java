package com.example.timingapplication.Service;

import com.example.timingapplication.Annotation.TrackAsyncTime;
import com.example.timingapplication.Annotation.TrackTime;
import com.example.timingapplication.Model.Plant;
import com.example.timingapplication.Model.PlantException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class PlantService {

    private final Map<String, Plant> plants = new HashMap<>();

    @TrackTime
    public void addPlant(Plant plant){
        plants.put(plant.getName(), plant);
    }

    @TrackTime
    public void addPlants(List<Plant> newPlants) {
        if (newPlants.size() == 1){
            throw new PlantException("Используйте метод addPlant(Plant plant)");
        }

        plants.putAll(newPlants.stream().collect(Collectors.toMap(Plant::getName, Function.identity())));
    }

    @TrackTime
    public Plant getPlantByName(String name){
        return plants.get(name);
    }

    @TrackTime
    public List<Plant> getPlantsByType(String type){
        return plants.values().stream().filter(plant -> plant.getType().equals(type)).collect(Collectors.toList());
    }

    @TrackAsyncTime
    public void updatePlant(String name, Plant updatedPlant){
        if (!plants.containsKey(name)) {
            throw new PlantException("Растение с названием " + name + " не найдено.");
        }

        plants.put(name, updatedPlant);
    }

    @TrackAsyncTime
    public void removePlant(String name){
        if (!plants.containsKey(name)) {
            throw new PlantException("Растение с названием " + name + " не найдено.");
        }

        plants.remove(name);
    }

    @TrackAsyncTime
    public List<Plant> getAllPlants(){
        return new ArrayList<>(plants.values());
    }
}

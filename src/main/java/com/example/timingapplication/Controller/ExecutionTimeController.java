package com.example.timingapplication.Controller;

import com.example.timingapplication.Model.ExecutionTime;
import com.example.timingapplication.Model.TimeDTO;
import com.example.timingapplication.Repository.ExecutionTimeRepository;
import com.example.timingapplication.Service.ExecutionInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/execution-times")
public class ExecutionTimeController {
    private final ExecutionInfoService executionInfoService;

    @GetMapping("/{className}/{methodName}")
    public ResponseEntity<ExecutionTime> getMethodTime(
            @PathVariable("className") String className,
            @PathVariable("methodName") String methodName) {
        ExecutionTime executionTime = executionInfoService.getByMethodNameAndClassName(methodName, className);
        if (executionTime != null) {
            return ResponseEntity.ok(executionTime);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{methodName}")
    public ResponseEntity<List<ExecutionTime>> getMethodTimeByMethodName(
            @PathVariable("methodName") String methodName) {
        List<ExecutionTime> executionTimes = executionInfoService.getAllMethodTime(methodName);
        if (!executionTimes.isEmpty()) {
            return ResponseEntity.ok(executionTimes);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/averagetime/{className}/{methodName}")
    public ResponseEntity<TimeDTO<Float>> getAvgMethodTime(
            @PathVariable("methodName") String methodName,
            @PathVariable("className") String className) {
        TimeDTO<Float> avgMethodTime = executionInfoService.getAvgMethodTime(methodName, className);
        return ResponseEntity.ok(avgMethodTime);
    }

    @GetMapping("/total/{className}")
    public ResponseEntity<TimeDTO<Long>> getTimeOfAllMethodsInClass(
            @PathVariable("className") String className) {
        TimeDTO<Long> totalTime = executionInfoService.getTimeOfAllMethodsInClass(className);
        return ResponseEntity.ok(totalTime);
    }
}
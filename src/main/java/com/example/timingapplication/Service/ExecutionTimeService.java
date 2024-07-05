package com.example.timingapplication.Service;

import com.example.timingapplication.Model.ExecutionTime;
import com.example.timingapplication.Repository.ExecutionTimeRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ExecutionTimeService {
    private final ExecutionTimeRepository executionTimeRepository;

    @Transactional
    public void saveMethod(String methodName, String className, Long time){
        ExecutionTime executionTime;

        if (executionTimeRepository.existsByMethodNameAndClassName(methodName, className)){
            executionTime = executionTimeRepository.findByMethodNameAndClassName(methodName, className);
            int callCount = executionTime.getCallCount();
            long workTime = executionTime.getWorkTime();

            executionTime.setWorkTime(workTime + time);
            executionTime.setCallCount(callCount + 1);
        }
        else{
            executionTime = new ExecutionTime(methodName, className, 1, time);
        }

        executionTimeRepository.save(executionTime);
    }
}

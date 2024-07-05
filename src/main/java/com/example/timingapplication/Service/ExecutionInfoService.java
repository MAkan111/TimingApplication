package com.example.timingapplication.Service;

import com.example.timingapplication.Model.ExecutionTime;
import com.example.timingapplication.Model.ModelNotExistException;
import com.example.timingapplication.Model.TimeDTO;
import com.example.timingapplication.Repository.ExecutionTimeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@Service
@RequiredArgsConstructor
public class ExecutionInfoService {
    private final ExecutionTimeRepository executionTimeRepository;

    public ExecutionTime getByMethodNameAndClassName(String methodName, String className){
        ExecutionTime executionTime = executionTimeRepository.findByMethodNameAndClassName(methodName,className);
        if (executionTime == null){
            throw new ModelNotExistException("Такого метода не существует");
        }

        return executionTime;
    }

    public TimeDTO<Long> getTimeOfAllMethodsInClass(String className){
        AtomicLong time = new AtomicLong(0);
        executionTimeRepository.findAllByClassName(className).forEach(method -> time.addAndGet(method.getWorkTime()));

        return new TimeDTO<>(time.get());
    }

    public TimeDTO<Float> getAvgMethodTime(String methodName, String className){
        ExecutionTime executionTime = executionTimeRepository.findByMethodNameAndClassName(methodName, className);
        if (executionTime == null){
            throw new ModelNotExistException("Такого метода не существует");
        }

        return new TimeDTO<>((float) executionTime.getWorkTime() / executionTime.getCallCount());
    }

    public List<ExecutionTime> getAllMethodTime(String methodName){
        List<ExecutionTime> methodTimeEntities = executionTimeRepository.findAllByMethodName(methodName);
        if (methodTimeEntities.size() == 0){
            throw new ModelNotExistException("Таких методов не существует");
        }

        return executionTimeRepository.findAllByMethodName(methodName);
    }
}

package com.example.timingapplication.Repository;

import com.example.timingapplication.Model.ExecutionTime;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExecutionTimeRepository extends JpaRepository<ExecutionTime, Long> {
    List<ExecutionTime> findAllByClassName(String className);

    List<ExecutionTime> findAllByMethodName(String methodName);
    ExecutionTime findByMethodNameAndClassName(String methodName, String className);
    boolean existsByMethodNameAndClassName(String methodName, String className);
}

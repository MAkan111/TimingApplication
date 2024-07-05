package com.example.timingapplication.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "execution_time", schema = "public", catalog = "postgres")
@NoArgsConstructor
public class ExecutionTime {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private int id;
    @Basic
    @Column(name = "method_name", nullable = false, length = 255)
    private String methodName;
    @Basic
    @Column(name = "class_name", nullable = false, length = 255)
    private String className;
    @Basic
    @Column(name = "call_count", nullable = false)
    private int callCount;
    @Basic
    @Column(name = "work_time", nullable = false)
    private long workTime;

    public ExecutionTime(String methodName, String className, int callCount, long workTime){
        this.methodName = methodName;
        this.className = className;
        this.callCount = callCount;
        this.workTime = workTime;
    }
}

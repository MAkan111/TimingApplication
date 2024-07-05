package com.example.timingapplication.Aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Order(1)
@Slf4j
public class ExceptionAspect {
    @AfterThrowing(pointcut = "@annotation(com.openschool.homework1.annotation.Throw)", throwing = "exception")
    public void afterThrowing(JoinPoint joinPoint, Exception exception) {
        log.info("Произошла ошибка при вызове метода: {}", joinPoint.getSignature().toShortString());
        log.info("Ошибка: {}", exception.getMessage());
    }
}

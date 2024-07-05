package com.example.timingapplication.Aspect;

import com.example.timingapplication.Service.ExecutionTimeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Component
@Aspect
@Slf4j
@RequiredArgsConstructor
public class TrackTimeAspect {
    private final ExecutionTimeService executionTimeService;
    @Pointcut("@annotation(com.openschool.timingapplication.annotation.TrackTime)")
    public void trackTimePointcut() {}

    @Around("trackTimePointcut()")
    public Object trackTimePointcutRunner(ProceedingJoinPoint joinPoint) throws Throwable {
        long startTime = System.currentTimeMillis();

        String methodName = joinPoint.getSignature().getName();
        String className = joinPoint.getTarget().getClass().getSimpleName();
        Object[] methodArgs = joinPoint.getArgs();

        log.info("Выполнение метода {} с аргументами {}", methodName, methodArgs);

        Object result = joinPoint.proceed();

        long endTime = System.currentTimeMillis();

        log.info("Метод {} выполнился за {} мс с результатом {}", methodName, endTime - startTime, result);
        executionTimeService.saveMethod(methodName, className, endTime - startTime);

        return result;
    }
}

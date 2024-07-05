package com.example.timingapplication.Aspect;

import com.example.timingapplication.Service.ExecutionTimeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.concurrent.CompletableFuture;

@Component
@Aspect
@Slf4j
@RequiredArgsConstructor
public class TrackAsyncTimeAspect {
    private final ExecutionTimeService executionTimeService;

    @Pointcut("@annotation(com.openschool.homework1.annotation.TrackAsyncTime)")
    public void trackAsyncTimePointcut() {}

    @Around("trackAsyncTimePointcut()")
    public Object trackAsyncTimePointcutRunner(ProceedingJoinPoint joinPoint) throws Throwable {
        long startTime = System.currentTimeMillis();

        String methodName = joinPoint.getSignature().getName();
        String className = joinPoint.getTarget().getClass().getSimpleName();
        Object[] methodArgs = joinPoint.getArgs();

        log.info("Выполнение метода {} с аргументами {}", methodName, methodArgs);

        CompletableFuture<Object> result = CompletableFuture.supplyAsync(() -> {
            try {
                return joinPoint.proceed();
            } catch (Throwable throwable) {
                throw new RuntimeException(throwable);
            }
        });

        result.thenAcceptAsync((res) -> {
            long endTime = System.currentTimeMillis();
            log.info("Метод {} выполнился асинхронно за {} мс с результатом {}", methodName, endTime - startTime, res);
            executionTimeService.saveMethod(methodName, className, endTime - startTime);
        });

        return result.get();
    }
}

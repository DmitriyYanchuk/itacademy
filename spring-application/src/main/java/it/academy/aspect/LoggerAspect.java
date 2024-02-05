package it.academy.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

@Component
@Aspect
@Validated
public class LoggerAspect {

    private static final Logger LOGGER = LoggerFactory.getLogger(LoggerAspect.class);
    @Pointcut("@annotation(by.itacademy.aspect.annotation.ExecutionTime)")
    public void addExecution() {


    }

    @Around("addExecution()")
    public Object printResult(final ProceedingJoinPoint joinPoint) throws Throwable {
        final long startTime = System.nanoTime();
        try {

            return joinPoint.proceed();
        } finally {
            final long executionTime = System.nanoTime() - startTime;
            System.out.println("executionTime = " + executionTime);
            LOGGER.debug("[{}] executed in [{}] ns", joinPoint.getSignature(), executionTime);
        }
    }
}

package com.angus.shinnosuke.toolkit.service.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

import javax.annotation.PostConstruct;
import java.util.Arrays;

/**
 * Service Aspect
 */
@Slf4j
@Aspect
public class MethodAspect {

    @PostConstruct
    public void postConstruct() {
        log.debug("Start service aspect...");
    }

    @Pointcut("@within(org.springframework.stereotype.Service)")
    public void pointcut() {
    }

    @Before("pointcut()")
    public void doBefore(JoinPoint joinPoint) {
        long threadId = Thread.currentThread().getId();
        log.info("[{}] Call <---", threadId);
        log.info("[{}] Method: {}", threadId, joinPoint.getSignature());
        log.info("[{}] Args: {}", threadId, Arrays.toString(joinPoint.getArgs()));
    }

    @AfterReturning(pointcut = "pointcut()", returning = "returning")
    public void doAfterReturning(Object returning) {
        long threadId = Thread.currentThread().getId();
        log.info("[{}] Back --->", threadId);
        log.info("[{}] Return: {}", threadId, returning);
    }
}

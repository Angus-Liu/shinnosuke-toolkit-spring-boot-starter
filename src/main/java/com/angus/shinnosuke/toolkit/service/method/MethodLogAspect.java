package com.angus.shinnosuke.toolkit.service.method;

import com.angus.shinnosuke.toolkit.common.utils.JoinPointUtil;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

import javax.annotation.PostConstruct;
import java.util.Arrays;

/**
 * Service Method Log Aspect
 */
@Aspect
@Slf4j
@AllArgsConstructor
public class MethodLogAspect {

    private final MethodLogProperties props;

    @PostConstruct
    public void postConstruct() {
        log.info("Start service method log aspect...");
    }

    @Pointcut("@within(org.springframework.stereotype.Service)")
    public void pointcut() {
    }

    @Before("pointcut()")
    public void doBefore(JoinPoint jp) {
        if(JoinPointUtil.isIgnored(jp, props.getIgnoredItems())) return;
        long threadId = Thread.currentThread().getId();
        log.info("[{}] Call <---", threadId);
        log.info("[{}] Method: {}", threadId, jp.getSignature());
        log.info("[{}] Args: {}", threadId, Arrays.toString(jp.getArgs()));
    }

    @AfterReturning(pointcut = "pointcut()", returning = "returning")
    public void doAfterReturning(JoinPoint jp, Object returning) {
        if(JoinPointUtil.isIgnored(jp, props.getIgnoredItems())) return;
        long threadId = Thread.currentThread().getId();
        log.info("[{}] Back --->", threadId);
        log.info("[{}] Return: {}", threadId, returning);
    }
}

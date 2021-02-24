package com.angus.shinnosuke.toolkit.controller.aspect;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

/**
 * Controller Method Aspect
 */
@Aspect
@Slf4j
@AllArgsConstructor
public class MethodAspect {

    private final MethodAspectProperties aspectProperties;

    @PostConstruct
    public void postConstruct() {
        log.info("Start controller method aspect...");
    }

    @Pointcut("@within(org.springframework.stereotype.Controller) || @within(org.springframework.web.bind.annotation.RestController)")
    public void pointcut() {
    }

    @Before("pointcut()")
    public void doBefore(JoinPoint joinPoint) {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        assert attributes != null;
        HttpServletRequest request = attributes.getRequest();
        String sessionId = request.getSession().getId();
        log.info("[{}] Request <---", sessionId);
        log.info("[{}] IP: {}", sessionId, request.getRemoteAddr());
        log.info("[{}] URI: {} {}", sessionId, request.getMethod(), request.getRequestURI());
        log.info("[{}] Method: {}", sessionId, joinPoint.getSignature());
        log.info("[{}] Args: {}", sessionId, Arrays.toString(joinPoint.getArgs()));
    }

    @AfterReturning(pointcut = "pointcut()", returning = "response")
    public void doAfterReturning(Object response) {
        RequestAttributes attributes = RequestContextHolder.getRequestAttributes();
        assert attributes != null;
        String sessionId = attributes.getSessionId();
        log.info("[{}] Response --->", sessionId);
        log.info("[{}] Result: {}", sessionId, response);
    }

    @AfterThrowing(pointcut = "pointcut()", throwing = "exception")
    public void doAfterThrowing(Exception exception) {
        RequestAttributes attributes = RequestContextHolder.getRequestAttributes();
        assert attributes != null;
        String sessionId = attributes.getSessionId();
        log.info("[{}] Response --->", sessionId);
        if (aspectProperties.isCompletedStackTrace()) {
            log.error("[{}] Exception:", sessionId, exception);
        } else {
            log.error("[{}] Exception: {}", sessionId, exception.toString());
        }
    }
}

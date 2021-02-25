package com.angus.shinnosuke.toolkit.controller.method;

import com.angus.shinnosuke.toolkit.utils.JoinPointUtil;
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
 * Controller Method Log Aspect
 */
@Aspect
@Slf4j
@AllArgsConstructor
public class MethodLogAspect {

    private final MethodLogProperties props;

    @PostConstruct
    public void postConstruct() {
        log.info("Start controller method log aspect...");
    }

    @Pointcut("@within(org.springframework.stereotype.Controller) || @within(org.springframework.web.bind.annotation.RestController)")
    public void pointcut() {
    }

    @Before("pointcut()")
    public void doBefore(JoinPoint jp) {
        if(JoinPointUtil.isIgnored(jp, props.getIgnoredItems())) return;
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        assert attributes != null;
        HttpServletRequest request = attributes.getRequest();
        String sessionId = request.getSession().getId();
        log.info("[{}] Request <---", sessionId);
        log.info("[{}] IP: {}", sessionId, request.getRemoteAddr());
        log.info("[{}] URI: {} {}", sessionId, request.getMethod(), request.getRequestURI());
        log.info("[{}] Method: {}", sessionId, jp.getSignature());
        log.info("[{}] Args: {}", sessionId, Arrays.toString(jp.getArgs()));
    }

    @AfterReturning(pointcut = "pointcut()", returning = "response")
    public void doAfterReturning(JoinPoint jp, Object response) {
        if(JoinPointUtil.isIgnored(jp, props.getIgnoredItems())) return;
        RequestAttributes attributes = RequestContextHolder.getRequestAttributes();
        assert attributes != null;
        String sessionId = attributes.getSessionId();
        log.info("[{}] Response --->", sessionId);
        log.info("[{}] Result: {}", sessionId, response);
    }

    @AfterThrowing(pointcut = "pointcut()", throwing = "exception")
    public void doAfterThrowing(JoinPoint jp, Exception exception) {
        if(JoinPointUtil.isIgnored(jp, props.getIgnoredItems())) return;
        RequestAttributes attributes = RequestContextHolder.getRequestAttributes();
        assert attributes != null;
        String sessionId = attributes.getSessionId();
        log.info("[{}] Response --->", sessionId);
        if (props.isCompletedStackTrace()) {
            log.error("[{}] Exception:", sessionId, exception);
        } else {
            log.error("[{}] Exception: {}", sessionId, exception.toString());
        }
    }
}

package com.angus.shinnosuke.toolkit.cache;

import com.google.common.base.Strings;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

/**
 * Thread Local Cache
 */
@Aspect
public class MethodCacheAspect {
    // TODO: angus on 2021/2/26 线程复用问题
    // TODO: angus on 2021/2/26 缓存过期
    // TODO: angus on 2021/2/26 标识 ID 机制（Request Session ID）

    private static final String SEPARATOR = "-";

    private String genKey(JoinPoint jp, MethodCache tc) {
        StringBuilder keyBuilder = new StringBuilder();
        if (!Strings.isNullOrEmpty(tc.prefix())) {
            keyBuilder.append(tc.prefix()).append(SEPARATOR);
        }
        if (tc.key() == KeyType.METHOD_PARAM_NAME) {
            keyBuilder.append(jp.getSignature().toLongString());
        }
        return keyBuilder.toString();
    }

    @Around("@annotation(threadLocalCache)")
    public Object around(ProceedingJoinPoint jp, MethodCache threadLocalCache) throws Throwable {
        String fullKey = threadLocalCache.prefix() + SEPARATOR + jp.getSignature().toLongString();
        return jp.proceed();
    }
}

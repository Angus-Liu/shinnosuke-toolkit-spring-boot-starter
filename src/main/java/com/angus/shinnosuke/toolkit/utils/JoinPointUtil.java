package com.angus.shinnosuke.toolkit.utils;

import com.angus.shinnosuke.toolkit.controller.method.MethodLogProperties;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;

import java.util.List;

/**
 * Join Point Util
 */
@Slf4j
public class JoinPointUtil {

    /**
     * Get declaring type with method name, such as 'com.example.Demo.hello()'
     */
    private static String getDeclaringTypeWithMethodName(JoinPoint joinPoint) {
        String declaringTypeName = joinPoint.getSignature().getDeclaringTypeName();
        String methodName = joinPoint.getSignature().getName();
        return declaringTypeName + "." + methodName + "()";
    }

    /**
     * Check if the declaring type with method name of the join point is in ignored items
     *
     * @param ignoredItems Format same as {@link MethodLogProperties.ignoredItems}
     */
    public static boolean isIgnored(JoinPoint joinPoint, List<String> ignoredItems) {
        if (ignoredItems.isEmpty()) return false;
        String fullName = getDeclaringTypeWithMethodName(joinPoint);
        return ignoredItems.stream().anyMatch(fullName::startsWith);
    }
}

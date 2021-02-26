package com.angus.shinnosuke.toolkit.common.utils;

import org.springframework.aop.Pointcut;
import org.springframework.aop.support.ComposablePointcut;
import org.springframework.aop.support.annotation.AnnotationMatchingPointcut;
import org.springframework.util.Assert;

import java.lang.annotation.Annotation;

/**
 * Pointcut Util
 */
public class PointcutUtil {

    /**
     * Build annotation matching pointcut for class or method (or both)
     */
    public static Pointcut annotationMatchingPointcut(
            Class<? extends Annotation> annotationType,
            boolean matchClass,
            boolean matchMethod,
            boolean checkInherited
    ) {
        Assert.isTrue((matchClass || matchMethod), "Either Class or Method needs to be match (or both)");
        // The pointcut that class annotated by the specified annotation (include its children type)
        Pointcut classPointcut = matchClass ? new AnnotationMatchingPointcut(annotationType, null, checkInherited) : null;
        // The pointcut that method annotated by the specified annotation (include its children type)
        Pointcut methodPointcut = matchMethod ? new AnnotationMatchingPointcut(null, annotationType, checkInherited) : null;
        if (classPointcut != null && methodPointcut != null) {
            return new ComposablePointcut(classPointcut).union(methodPointcut);
        } else if (classPointcut != null) {
            return classPointcut;
        } else {
            return methodPointcut;
        }
    }
}
package com.angus.shinnosuke.toolkit.datasource.using;

import com.angus.shinnosuke.toolkit.datasource.producer.DynamicRoutingDataSource;
import lombok.extern.slf4j.Slf4j;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.core.annotation.AnnotatedElementUtils;
import org.springframework.core.annotation.AnnotationAttributes;
import org.springframework.util.Assert;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.lang.reflect.Method;

/**
 * Using Data Source Method Interceptor
 */
@Slf4j
public class UsingDataSourceMethodInterceptor implements MethodInterceptor {
    @Nullable
    @Override
    public Object invoke(@Nonnull MethodInvocation methodInvocation) throws Throwable {
        Method method = methodInvocation.getMethod();
        AnnotationAttributes attributes = AnnotatedElementUtils.getMergedAnnotationAttributes(method, UsingDataSource.class);
        if (attributes == null) {
            Class<?> declaringClass = method.getDeclaringClass();
            attributes = AnnotatedElementUtils.getMergedAnnotationAttributes(declaringClass, UsingDataSource.class);
        }
        Assert.notNull(attributes, UsingDataSource.class.getName() + " annotation not found");
        String type = attributes.getString("value");
        log.debug("data source type is {}", type);
        // Save the data source type to switch to
        // TODO: angus on 2021/2/25 Data source type stack to solve nest call
        DynamicRoutingDataSource.TYPE_HOLDER.set(type);
        Object res;
        try {
            res = methodInvocation.proceed();
        } finally {
            // Restore to the default data source
            DynamicRoutingDataSource.TYPE_HOLDER.remove();
        }
        return res;
    }
}

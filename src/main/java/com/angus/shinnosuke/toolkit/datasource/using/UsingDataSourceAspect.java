package com.angus.shinnosuke.toolkit.datasource.using;

import com.angus.shinnosuke.toolkit.datasource.producer.DynamicRoutingDataSource;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

import static com.angus.shinnosuke.toolkit.datasource.props.MultiDataSourceProperties.PREFIX;

/**
 * Data source switching aspect, work with {@link UsingDataSource}
 * and {@link DynamicRoutingDataSource} to switch data source
 */
@Slf4j
@Aspect
@Component
@ConditionalOnProperty(prefix = PREFIX, name = "use-custom-annotation", havingValue = "false", matchIfMissing = true)
public class UsingDataSourceAspect {

    @Around("@annotation(usingDataSource)")
    public Object around(ProceedingJoinPoint joinPoint, UsingDataSource usingDataSource) throws Throwable {
        String type = usingDataSource.value();
        log.debug("data source type is {}", type);
        // Save the data source type to switch to
        // TODO: angus on 2021/2/25 Data source type stack to solve nest call
        DynamicRoutingDataSource.TYPE_HOLDER.set(type);
        Object res;
        try {
            res = joinPoint.proceed();
        } finally {
            // Restore to the default data source
            DynamicRoutingDataSource.TYPE_HOLDER.remove();
        }
        return res;
    }
}

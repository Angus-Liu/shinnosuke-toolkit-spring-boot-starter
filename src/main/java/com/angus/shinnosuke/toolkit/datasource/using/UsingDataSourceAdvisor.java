package com.angus.shinnosuke.toolkit.datasource.using;

import com.angus.shinnosuke.toolkit.common.utils.PointcutUtil;
import lombok.extern.slf4j.Slf4j;
import org.aopalliance.aop.Advice;
import org.springframework.aop.Pointcut;
import org.springframework.aop.support.AbstractPointcutAdvisor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

import javax.annotation.Nonnull;
import javax.annotation.PostConstruct;

import static com.angus.shinnosuke.toolkit.datasource.props.MultiDataSourceProperties.PREFIX;

/**
 * Using Data Source Advisor
 */
@Slf4j
@Component
@ConditionalOnProperty(prefix = PREFIX, name = "use-custom-annotation", havingValue = "true")
public class UsingDataSourceAdvisor extends AbstractPointcutAdvisor {

    private final Pointcut pointcut = PointcutUtil.annotationMatchingPointcut(UsingDataSource.class, true, true, true);

    private final Advice advice = new UsingDataSourceMethodInterceptor();

    @Nonnull
    @Override
    public Pointcut getPointcut() {
        return pointcut;
    }

    @Nonnull
    @Override
    public Advice getAdvice() {
        return advice;
    }

    @PostConstruct
    private void postConstruct() {
        log.debug("Use custom annotation, pointcut is {}, advice is {}", pointcut, advice);
    }
}

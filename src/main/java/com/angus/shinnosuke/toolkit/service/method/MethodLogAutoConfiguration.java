package com.angus.shinnosuke.toolkit.service.method;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import javax.annotation.PostConstruct;

/**
 * Service Method Log Auto Configuration
 */
@Slf4j
@AllArgsConstructor
@Configuration
@ConditionalOnProperty(prefix = MethodLogProperties.PREFIX, name = "enable")
@Import(MethodLogAspect.class)
@EnableConfigurationProperties(MethodLogProperties.class)
public class MethodLogAutoConfiguration {

    private final MethodLogProperties logProperties;

    @PostConstruct
    public void postConstruct() {
        log.debug("Load service method log auto configuration properties {}", logProperties);
    }
}

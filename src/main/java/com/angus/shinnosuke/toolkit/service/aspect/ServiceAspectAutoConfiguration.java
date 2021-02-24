package com.angus.shinnosuke.toolkit.service.aspect;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import javax.annotation.PostConstruct;

/**
 * Service Aspect Auto Configuration
 */
@Slf4j
@AllArgsConstructor
@Configuration
@ConditionalOnProperty(prefix = ServiceAspectProperties.PREFIX, name = "enable")
@Import(ServiceAspect.class)
@EnableConfigurationProperties(ServiceAspectProperties.class)
public class ServiceAspectAutoConfiguration {

    private final ServiceAspectProperties controllerAspectProperties;

    @PostConstruct
    public void postConstruct() {
        log.debug("Load service aspect auto configuration properties: {}", controllerAspectProperties);
    }
}

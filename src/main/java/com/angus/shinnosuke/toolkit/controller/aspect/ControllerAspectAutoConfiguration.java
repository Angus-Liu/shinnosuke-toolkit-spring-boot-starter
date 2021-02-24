package com.angus.shinnosuke.toolkit.controller.aspect;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import javax.annotation.PostConstruct;

/**
 * Controller Aspect Auto Configuration
 */
@Slf4j
@AllArgsConstructor
@Configuration
@ConditionalOnProperty(prefix = ControllerAspectProperties.PREFIX, name = "enable")
@Import(ControllerAspect.class)
@EnableConfigurationProperties(ControllerAspectProperties.class)
public class ControllerAspectAutoConfiguration {

    private final ControllerAspectProperties controllerAspectProperties;

    @PostConstruct
    public void postConstruct() {
        log.debug("Load controller aspect auto configuration properties: {}", controllerAspectProperties);
    }
}

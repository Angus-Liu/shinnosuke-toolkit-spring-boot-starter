package com.angus.shinnosuke.toolkit.service.aspect;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Service Aspect Properties
 */
@Data
@ConfigurationProperties(MethodAspectProperties.PREFIX)
public class MethodAspectProperties {

    public static final String PREFIX = "spring.shinnosuke-toolkit.service.method-aspect";

    /**
     * Enable service aspect
     */
    private boolean enable = false;
}

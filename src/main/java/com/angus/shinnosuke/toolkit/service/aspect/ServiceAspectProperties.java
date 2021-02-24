package com.angus.shinnosuke.toolkit.service.aspect;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Service Aspect Properties
 */
@Data
@ConfigurationProperties(ServiceAspectProperties.PREFIX)
public class ServiceAspectProperties {

    public static final String PREFIX = "spring.shinnosuke-toolkit.service.aspect";

    /**
     * Enable service aspect
     */
    private boolean enable = false;
}

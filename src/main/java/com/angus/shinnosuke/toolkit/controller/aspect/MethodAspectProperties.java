package com.angus.shinnosuke.toolkit.controller.aspect;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;


/**
 * Controller Method Aspect Properties
 */
@Data
@ConfigurationProperties(MethodAspectProperties.PREFIX)
public class MethodAspectProperties {

    public static final String PREFIX = "spring.shinnosuke-toolkit.controller.method-aspect";

    /**
     * Enable controller method aspect
     */
    private boolean enable = false;
}

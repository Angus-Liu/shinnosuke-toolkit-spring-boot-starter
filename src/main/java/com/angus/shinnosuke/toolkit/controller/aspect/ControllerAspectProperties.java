package com.angus.shinnosuke.toolkit.controller.aspect;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Controller Aspect Properties
 */
@Data
@ConfigurationProperties(ControllerAspectProperties.PREFIX)
public class ControllerAspectProperties {

    public static final String PREFIX = "spring.shinnosuke-toolkit.controller.aspect";
    
    /**
     * Enable controller aspect
     */
    private boolean enable = false;
}

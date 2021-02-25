package com.angus.shinnosuke.toolkit.controller.method;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.Collections;
import java.util.List;


/**
 * Controller Method Log Properties
 */
@Data
@ConfigurationProperties(MethodLogProperties.PREFIX)
public class MethodLogProperties {

    public static final String PREFIX = "spring.shinnosuke-toolkit.controller.method-log";

    /**
     * Enable controller method log
     */
    private boolean enable = false;

    /**
     * Enable print completed stack trace when exception occurs
     */
    private boolean completedStackTrace = false;

    // TODO: angus on 2021/2/25 ignorePackages & ignoreClasses

    private List<String> ignorePackages = Collections.emptyList();

    private List<Class<?>> ignoreClasses = Collections.emptyList();
}

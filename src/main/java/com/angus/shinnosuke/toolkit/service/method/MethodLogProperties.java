package com.angus.shinnosuke.toolkit.service.method;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.Collections;
import java.util.List;

/**
 * Service Method Log Properties
 */
@Data
@ConfigurationProperties(MethodLogProperties.PREFIX)
public class MethodLogProperties {

    public static final String PREFIX = "spring.shinnosuke-toolkit.service.method-log";

    /**
     * Enable service method log
     */
    private boolean enable = false;

    // TODO: angus on 2021/2/25 ignorePackages & ignoreClasses

    private List<String> ignorePackages = Collections.emptyList();

    private List<Class<?>> ignoreClasses = Collections.emptyList();
}

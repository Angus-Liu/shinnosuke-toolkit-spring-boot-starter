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

    /**
     * ignore packages, classes or methods.
     * methods need end with "()", overloaded methods are not distinguished for now. <br/>
     * for example: <br/>
     *  - com.example <br/>
     *  - com.example.DemoController <br/>
     *  - com.example.DemoController.helloWorld()
     */
    private List<String> ignoredItems = Collections.emptyList();
}

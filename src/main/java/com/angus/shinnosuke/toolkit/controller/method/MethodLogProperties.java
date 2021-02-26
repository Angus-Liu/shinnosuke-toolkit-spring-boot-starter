package com.angus.shinnosuke.toolkit.controller.method;

import com.angus.shinnosuke.toolkit.common.consts.GlobalConst;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;

import javax.annotation.PostConstruct;
import java.util.Collections;
import java.util.List;


/**
 * Controller Method Log Properties
 */
@Slf4j
@Data
@ConfigurationProperties(MethodLogProperties.PREFIX)
public class MethodLogProperties {

    public static final String PREFIX = GlobalConst.PREFIX + "controller.method-log";

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
     * - com.example <br/>
     * - com.example.DemoController <br/>
     * - com.example.DemoController.helloWorld()
     */
    private List<String> ignoredItems = Collections.emptyList();

    @PostConstruct
    public void postConstruct() {
        log.debug("Load controller method log auto configuration properties {}", this);
    }
}

package com.angus.shinnosuke.toolkit.service.method;

import com.angus.shinnosuke.toolkit.consts.GlobalConst;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;

import javax.annotation.PostConstruct;
import java.util.Collections;
import java.util.List;

/**
 * Service Method Log Properties
 */
@Slf4j
@Data
@ConfigurationProperties(MethodLogProperties.PREFIX)
public class MethodLogProperties {

    public static final String PREFIX = GlobalConst.PREFIX + "service.method-log";

    /**
     * Enable service method log
     */
    private boolean enable = false;

    /**
     * ignore packages, classes or methods.
     * methods need end with "()", overloaded methods are not distinguished for now. <br/>
     * for example: <br/>
     * - com.example <br/>
     * - com.example.DemoService <br/>
     * - com.example.DemoService.helloWorld()
     */
    private List<String> ignoredItems = Collections.emptyList();

    @PostConstruct
    public void postConstruct() {
        log.debug("Load service method log auto configuration properties {}", this);
    }
}

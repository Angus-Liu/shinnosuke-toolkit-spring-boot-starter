package com.angus.shinnosuke.toolkit.datasource.using;

import com.angus.shinnosuke.toolkit.datasource.producer.DynamicRoutingDataSource;
import org.springframework.core.annotation.AliasFor;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Specifies the use of data source type, work with {@link UsingDataSourceAspect}
 * and {@link DynamicRoutingDataSource} to switch data source
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface UsingDataSource {
    /**
     * data source name
     */
    @AliasFor("name")
    String value();

    @AliasFor("value")
    String name();
}

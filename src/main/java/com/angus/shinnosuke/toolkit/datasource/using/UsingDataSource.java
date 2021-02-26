package com.angus.shinnosuke.toolkit.datasource.using;

import com.angus.shinnosuke.toolkit.datasource.producer.DynamicRoutingDataSource;

import java.lang.annotation.*;

/**
 * Specifies the use of data source type, work with {@link UsingDataSourceAspect}
 * and {@link DynamicRoutingDataSource} to switch data source
 */
@Inherited
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface UsingDataSource {
    /**
     * data source name
     */
    String value();
}

package com.angus.shinnosuke.toolkit.datasource.props;

import com.alibaba.druid.pool.DruidDataSource;
import com.zaxxer.hikari.HikariDataSource;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.NestedConfigurationProperty;

/**
 * Single Data Source Properties
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class SingleDataSourceProperties extends DataSourceProperties {

    /**
     * Druid data source properties,
     * It's necessary when DataSourceProperties.type is 'com.alibaba.druid.pool.DruidDataSource'.
     */
    @NestedConfigurationProperty
    private DruidDataSource druid;

    /**
     * Hikari data source properties,
     * It's necessary when DataSourceProperties.type is 'com.zaxxer.hikari.HikariDataSource'.
     */
    @NestedConfigurationProperty
    private HikariDataSource hikari;
}

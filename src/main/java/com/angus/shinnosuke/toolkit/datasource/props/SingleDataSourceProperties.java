package com.angus.shinnosuke.toolkit.datasource.props;

import com.alibaba.druid.pool.DruidDataSource;
import com.zaxxer.hikari.HikariDataSource;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.NestedConfigurationProperty;


@Data
@EqualsAndHashCode(callSuper = true)
public class SingleDataSourceProperties extends DataSourceProperties {

    @NestedConfigurationProperty
    private DruidDataSource druid = new DruidDataSource();

    @NestedConfigurationProperty
    private HikariDataSource hikari = new HikariDataSource();
}

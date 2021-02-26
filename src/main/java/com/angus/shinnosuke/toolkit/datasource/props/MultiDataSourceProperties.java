package com.angus.shinnosuke.toolkit.datasource.props;

import com.alibaba.druid.pool.DruidDataSource;
import com.angus.shinnosuke.toolkit.common.consts.GlobalConst;
import com.zaxxer.hikari.HikariDataSource;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.NestedConfigurationProperty;

import javax.annotation.PostConstruct;
import java.util.Collections;
import java.util.Map;

/**
 * Multi Data Source Properties
 */
@Slf4j
@Data
@ConfigurationProperties(MultiDataSourceProperties.PREFIX)
public class MultiDataSourceProperties {

    public static final String PREFIX = GlobalConst.PREFIX + "multi-datasource";

    /**
     * Enable multi-datasource
     */
    private boolean enable = false;

    /**
     * Enable use custom annotation
     */
    private boolean useCustomAnnotation = false;

    /**
     * Default primary datasource name
     */
    private String primary = "master";

    /**
     * Datasource properties map. The key is name of the datasource, value is properties of the datasource
     */
    private Map<String, SingleDataSourceProperties> datasource = Collections.emptyMap();

    /**
     * // TODO: angus on 2021/2/25 Global druid config
     */
    @NestedConfigurationProperty
    private DruidDataSource druid = new DruidDataSource();

    /**
     * // TODO: angus on 2021/2/25 Global hikari config
     */
    @NestedConfigurationProperty
    private HikariDataSource hikari = new HikariDataSource();

    @PostConstruct
    public void postConstruct() {
        log.debug("Load multi-datasource auto configuration properties {}", this);
    }
}

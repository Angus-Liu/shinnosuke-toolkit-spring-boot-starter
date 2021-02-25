package com.angus.shinnosuke.toolkit.datasource.producer;

import com.angus.shinnosuke.toolkit.datasource.props.MultiDataSourceProperties;
import com.angus.shinnosuke.toolkit.datasource.using.UsingDataSource;
import com.angus.shinnosuke.toolkit.datasource.using.UsingDataSourceAspect;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * Dynamic routing data source configuration, work with {@link UsingDataSource}
 * and {@link UsingDataSourceAspect} to switch data source.
 */
@Primary
@Component
@Slf4j
@AllArgsConstructor
public class DynamicRoutingDataSource extends AbstractRoutingDataSource {

    private final MultiDataSourceProperties props;

    public final DataSourceProducer dataSourceProducer;

    public static final ThreadLocal<String> TYPE_HOLDER = new ThreadLocal<>();

    @Override
    protected Object determineCurrentLookupKey() {
        return TYPE_HOLDER.get();
    }

    @PostConstruct
    private void postConstruct() {
        log.info("Start dynamic routing datasource...");
        Map<String, DataSource> dataSourceMap = dataSourceProducer.createDataSourceMap(props);
        log.debug("Data source map is {}", dataSourceMap);
        // Set default data source
        DataSource defaultDataSource = dataSourceMap.get(props.getPrimary());
        setDefaultTargetDataSource(defaultDataSource);
        // Set data source map
        setTargetDataSources(new HashMap<>(dataSourceMap));
    }
}

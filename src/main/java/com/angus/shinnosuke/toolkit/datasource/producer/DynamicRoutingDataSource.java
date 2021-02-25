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
        log.info("Dynamic routing datasource starting...");
        Map<String, DataSource> dataSourceMap = dataSourceProducer.createDataSourceMap(props);
        // Set default data source
        String primary = props.getPrimary();
        DataSource defaultDataSource = dataSourceMap.get(primary);
        setDefaultTargetDataSource(defaultDataSource);
        // Set data source map
        setTargetDataSources(new HashMap<>(dataSourceMap));
        log.info(
                "Dynamic routing datasource start completed, loaded {} data sources ({}), primary is [{}].",
                dataSourceMap.size(),
                dataSourcesInfo(dataSourceMap),
                primary
        );
    }

    private String dataSourcesInfo(Map<String, DataSource> dataSourceMap) {
        StringBuilder sb = new StringBuilder();
        dataSourceMap.forEach((name, ds) ->
                sb.append(name).append(" - ").append(ds.getClass().getSimpleName()).append(", ")
        );
        return sb.delete(sb.length() - 2, sb.length()).toString();
    }
}

package com.angus.shinnosuke.toolkit.datasource.producer;

import com.angus.shinnosuke.toolkit.datasource.props.MultiDataSourceProperties;
import com.angus.shinnosuke.toolkit.datasource.props.SingleDataSourceProperties;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Data Source Producer
 */
@Slf4j
@Component
@AllArgsConstructor
public class DataSourceProducer {

    /**
     * All data source factories
     */
    private final List<BaseDataSourceFactory> factories;

    /**
     * Data source factory with its created data source type
     */
    private final Map<Class<? extends DataSource>, BaseDataSourceFactory> factoryMap = new HashMap<>();

    @PostConstruct
    void postConstruct() {
        // Map data source factory with its created data source type
        factories.forEach(factory -> factoryMap.put(factory.getCreatableType(), factory));
    }

    /**
     * Get to type of data source to create from props
     */
    private Class<? extends DataSource> toCreateType(SingleDataSourceProperties props) {
        if (props.getType() == null)
            return BasicDataSourceFactory.BasicDataSource.class;
        return props.getType();
    }

    /**
     * Create data sources from multi-datasource props and map them with their name
     */
    public Map<String, DataSource> createDataSourceMap(MultiDataSourceProperties multiProps) {
        Map<String, SingleDataSourceProperties> datasourceProps = multiProps.getDatasource();
        Map<String, DataSource> dataSourceMap = new HashMap<>();
        datasourceProps.forEach((name, prop) -> {
            Class<? extends DataSource> dsType = toCreateType(prop);
            DataSource dataSource = factoryMap.get(dsType).create(prop);
            dataSourceMap.put(name, dataSource);
        });
        return dataSourceMap;
    }
}

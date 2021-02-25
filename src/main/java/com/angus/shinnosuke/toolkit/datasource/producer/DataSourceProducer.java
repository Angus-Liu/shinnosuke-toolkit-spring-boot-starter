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

@Slf4j
@Component
@AllArgsConstructor
public class DataSourceProducer {

    private final List<BaseDataSourceFactory> factories;

    private final Map<Class<? extends DataSource>, BaseDataSourceFactory> factoryMap = new HashMap<>();

    @PostConstruct
    void postConstruct() {
        factories.forEach(factory -> factoryMap.put(factory.getType(), factory));
        log.debug("datasource factory map is {}", factoryMap);
    }

    private Class<? extends DataSource> getType(SingleDataSourceProperties props) {
        if (props.getType() == null)
            return BasicDataSourceFactory.BasicDataSource.class;
        return props.getType();
    }

    public Map<String, DataSource> createDataSourceMap(MultiDataSourceProperties multiProps) {
        Map<String, SingleDataSourceProperties> datasourceProps = multiProps.getDatasource();
        Map<String, DataSource> dataSourceMap = new HashMap<>();
        datasourceProps.forEach((name, prop) -> {
            Class<? extends DataSource> dsType = getType(prop);
            DataSource dataSource = factoryMap.get(dsType).create(prop);
            dataSourceMap.put(name, dataSource);
        });
        return dataSourceMap;
    }
}

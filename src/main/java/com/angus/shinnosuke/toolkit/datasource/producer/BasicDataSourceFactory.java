package com.angus.shinnosuke.toolkit.datasource.producer;

import com.angus.shinnosuke.toolkit.datasource.props.SingleDataSourceProperties;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

/**
 * Basic Data Source Factory
 */
@Component
public class BasicDataSourceFactory implements BaseDataSourceFactory {

    /**
     * Basic Data Source
     */
    abstract static class BasicDataSource implements DataSource {
    }

    @Override
    public Class<? extends DataSource> getCreatableType() {
        return BasicDataSource.class;
    }

    @Override
    public DataSource create(SingleDataSourceProperties props) {
        return props.initializeDataSourceBuilder().build();
    }
}

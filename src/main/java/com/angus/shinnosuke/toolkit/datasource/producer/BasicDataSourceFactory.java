package com.angus.shinnosuke.toolkit.datasource.producer;

import com.angus.shinnosuke.toolkit.datasource.props.SingleDataSourceProperties;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;


@Component
public class BasicDataSourceFactory implements BaseDataSourceFactory {

    abstract static class BasicDataSource implements DataSource {
    }

    @Override
    public Class<? extends DataSource> getType() {
        return BasicDataSource.class;
    }

    @Override
    public DataSource create(SingleDataSourceProperties props) {
        return props.initializeDataSourceBuilder().build();
    }
}

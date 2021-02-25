package com.angus.shinnosuke.toolkit.datasource.producer;

import com.angus.shinnosuke.toolkit.datasource.props.SingleDataSourceProperties;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

@Component
public class HikariDataSourceFactory implements BaseDataSourceFactory {

    @Override
    public Class<? extends DataSource> getType() {
        return HikariDataSource.class;
    }

    @Override
    public DataSource create(SingleDataSourceProperties props) {
        HikariDataSource hikariDataSource = props.getHikari();
        hikariDataSource.validate();
        return hikariDataSource;
    }
}

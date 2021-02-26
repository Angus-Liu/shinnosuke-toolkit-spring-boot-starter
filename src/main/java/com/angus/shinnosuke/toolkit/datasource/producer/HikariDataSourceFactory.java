package com.angus.shinnosuke.toolkit.datasource.producer;

import com.angus.shinnosuke.toolkit.datasource.props.SingleDataSourceProperties;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

/**
 * Hikari Data Source Factory
 */
@Component
public class HikariDataSourceFactory implements BaseDataSourceFactory {

    @Override
    public Class<? extends DataSource> getCreatableType() {
        return HikariDataSource.class;
    }

    @Override
    public DataSource create(SingleDataSourceProperties props) {
        HikariDataSource hikari = props.getHikari();
        hikari.validate();
        return hikari;
    }
}

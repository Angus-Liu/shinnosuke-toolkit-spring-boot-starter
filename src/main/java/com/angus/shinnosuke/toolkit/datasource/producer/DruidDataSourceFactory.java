package com.angus.shinnosuke.toolkit.datasource.producer;

import com.alibaba.druid.pool.DruidDataSource;
import com.angus.shinnosuke.toolkit.datasource.props.SingleDataSourceProperties;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.SQLException;

/**
 * Druid Data Source Factory
 */
@Component
public class DruidDataSourceFactory implements BaseDataSourceFactory {

    @Override
    public Class<? extends DataSource> getCreatableType() {
        return DruidDataSource.class;
    }

    @Override
    public DataSource create(SingleDataSourceProperties props) {
        DruidDataSource druid = props.getDruid();
        try {
            druid.init();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return druid;
    }
}

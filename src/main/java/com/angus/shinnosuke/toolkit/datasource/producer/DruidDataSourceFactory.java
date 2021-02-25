package com.angus.shinnosuke.toolkit.datasource.producer;

import com.alibaba.druid.pool.DruidDataSource;
import com.angus.shinnosuke.toolkit.datasource.props.SingleDataSourceProperties;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.SQLException;

@Component
public class DruidDataSourceFactory implements BaseDataSourceFactory {

    @Override
    public Class<? extends DataSource> getType() {
        return DruidDataSource.class;
    }

    @Override
    public DataSource create(SingleDataSourceProperties props) {
        DruidDataSource druidDataSource = props.getDruid();
        try {
            druidDataSource.init();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return druidDataSource;
    }
}

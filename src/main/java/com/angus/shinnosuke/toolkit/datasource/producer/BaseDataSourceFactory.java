package com.angus.shinnosuke.toolkit.datasource.producer;

import com.angus.shinnosuke.toolkit.datasource.props.SingleDataSourceProperties;

import javax.sql.DataSource;

public interface BaseDataSourceFactory {

    Class<? extends DataSource> getType();

    DataSource create(SingleDataSourceProperties props);
}

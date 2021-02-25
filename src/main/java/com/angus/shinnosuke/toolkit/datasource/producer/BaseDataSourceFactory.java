package com.angus.shinnosuke.toolkit.datasource.producer;

import com.angus.shinnosuke.toolkit.datasource.props.SingleDataSourceProperties;

import javax.sql.DataSource;

/**
 * Base Data Source Factory
 */
public interface BaseDataSourceFactory {

    /**
     * Get the class type of data source that can be created
     */
    Class<? extends DataSource> getCreatableType();

    /**
     * Create data source
     */
    DataSource create(SingleDataSourceProperties props);
}

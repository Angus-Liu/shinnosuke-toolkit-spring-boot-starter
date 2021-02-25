package com.angus.shinnosuke.toolkit.datasource;

import com.angus.shinnosuke.toolkit.datasource.props.MultiDataSourceProperties;
import com.angus.shinnosuke.toolkit.datasource.using.UsingDataSourceAspect;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * Multi-DataSource Auto Configuration
 */
@Slf4j
@Configuration
@AutoConfigureBefore(DataSourceAutoConfiguration.class)
@Import({DataSourceProducerConfiguration.class, UsingDataSourceAspect.class})
@EnableConfigurationProperties(MultiDataSourceProperties.class)
@ConditionalOnProperty(prefix = MultiDataSourceProperties.PREFIX, name = "enable")
public class MultiDataSourceAutoConfiguration {
}

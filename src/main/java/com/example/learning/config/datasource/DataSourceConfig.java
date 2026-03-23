package com.example.learning.config.datasource;

import com.example.learning.constants.DataSourceType;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.LazyConnectionDataSourceProxy;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class DataSourceConfig {

    @Bean
    @ConfigurationProperties(prefix = "spring.datasource.master")
    public DataSource masterDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean
    @ConfigurationProperties(prefix = "spring.datasource.slave")
    public DataSource slaveDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean
    public DataSource routingDataSource(
            @Qualifier("masterDataSource") DataSource master,
            @Qualifier("slaveDataSource") DataSource slave) {

        RoutingDataSource routingDataSource = new RoutingDataSource();

        Map<Object, Object> targetDataSources = new HashMap<>();
        targetDataSources.put(DataSourceType.MASTER, master);
        targetDataSources.put(DataSourceType.SLAVE, slave);

        routingDataSource.setTargetDataSources(targetDataSources);
        // Mặc định ban đầu là Master
        routingDataSource.setDefaultTargetDataSource(master);

        return routingDataSource;
    }

    @Bean
    @Primary // Đánh dấu đây là DataSource chính mà Spring/Hibernate sẽ dùng
    public DataSource dataSource(@Qualifier("routingDataSource") DataSource routingDataSource) {
        // Bắt buộc dùng Lazy để hoãn việc chọn Connection cho đến khi thực thi SQL
        return new LazyConnectionDataSourceProxy(routingDataSource);
    }
}
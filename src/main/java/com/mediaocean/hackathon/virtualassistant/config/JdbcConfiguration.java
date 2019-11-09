package com.mediaocean.hackathon.virtualassistant.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

@Configuration
public class JdbcConfiguration {

    @Bean
    public JdbcTemplate integrationJdbcTemplate(DataSource integrationDataSource) {
        return new JdbcTemplate(integrationDataSource);
    }

    @Bean
    public JdbcTemplate campaignJdbcTemplate(DataSource campaignDataSource) {
        return new JdbcTemplate(campaignDataSource);
    }

    @Bean
    public JdbcTemplate bridgeJdbcTemplate(DataSource bridgeDataSource) {
        return new JdbcTemplate(bridgeDataSource);
    }

    @Bean
    public JdbcTemplate deliveryJdbcTemplate(DataSource deliveryDataSource) {
        return new JdbcTemplate(deliveryDataSource);
    }
}

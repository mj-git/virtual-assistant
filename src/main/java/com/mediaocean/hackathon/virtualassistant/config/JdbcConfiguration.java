package com.mediaocean.hackathon.virtualassistant.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

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

    @Bean
    public NamedParameterJdbcTemplate integrationNamedParameterJdbcTemplate(DataSource dataSource) {
        return new NamedParameterJdbcTemplate(dataSource);
    }

    @Bean
    public NamedParameterJdbcTemplate campaignNamedParameterJdbcTemplate(DataSource campaignDataSource) {
        return new NamedParameterJdbcTemplate(campaignDataSource);
    }

    @Bean
    public NamedParameterJdbcTemplate bridgeNamedParameterJdbcTemplate(DataSource bridgeDataSource) {
        return new NamedParameterJdbcTemplate(bridgeDataSource);
    }

    @Bean
    public NamedParameterJdbcTemplate deliveryNamedParameterJdbcTemplate(DataSource deliveryDataSource) {
        return new NamedParameterJdbcTemplate(deliveryDataSource);
    }
}

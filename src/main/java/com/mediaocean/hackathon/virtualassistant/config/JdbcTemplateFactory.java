package com.mediaocean.hackathon.virtualassistant.config;

import com.mediaocean.hackathon.virtualassistant.enums.JdbcTemplateEnum;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import lombok.NonNull;

@Component
public class JdbcTemplateFactory {

    private final JdbcTemplate integrationJdbcTemplate;

    private final JdbcTemplate campaignJdbcTemplate;

    private final JdbcTemplate bridgeJdbcTemplate;

    private final JdbcTemplate deliveryJdbcTemplate;

    @Autowired
    public JdbcTemplateFactory(JdbcTemplate integrationJdbcTemplate, JdbcTemplate campaignJdbcTemplate, JdbcTemplate bridgeJdbcTemplate, JdbcTemplate deliveryJdbcTemplate) {
        this.integrationJdbcTemplate = integrationJdbcTemplate;
        this.campaignJdbcTemplate = campaignJdbcTemplate;
        this.bridgeJdbcTemplate = bridgeJdbcTemplate;
        this.deliveryJdbcTemplate = deliveryJdbcTemplate;
    }

    public JdbcTemplate getJdbcTemplate(@NonNull JdbcTemplateEnum templateEnum) {
        JdbcTemplate jdbcTemplate = null;
        switch (templateEnum) {
            case BRIDGE:
                jdbcTemplate = bridgeJdbcTemplate;
                break;
            case CAMPAIGN:
                jdbcTemplate = campaignJdbcTemplate;
                break;
            case DELIVERY:
                jdbcTemplate = deliveryJdbcTemplate;
                break;
            case INTEGRATION:
                jdbcTemplate = integrationJdbcTemplate;
                break;
        }
        return jdbcTemplate;
    }

}

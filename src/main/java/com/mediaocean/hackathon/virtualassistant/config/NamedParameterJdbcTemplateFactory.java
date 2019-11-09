package com.mediaocean.hackathon.virtualassistant.config;

import com.mediaocean.hackathon.virtualassistant.enums.JdbcTemplateEnum;

import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import lombok.NonNull;

@Component
public class NamedParameterJdbcTemplateFactory {

    private final NamedParameterJdbcTemplate integrationNamedParameterJdbcTemplate;

    private final NamedParameterJdbcTemplate campaignNamedParameterJdbcTemplate;

    private final NamedParameterJdbcTemplate bridgeNamedParameterJdbcTemplate;

    private final NamedParameterJdbcTemplate deliveryNamedParameterJdbcTemplate;

    public NamedParameterJdbcTemplateFactory(NamedParameterJdbcTemplate integrationNamedParameterJdbcTemplate, NamedParameterJdbcTemplate campaignNamedParameterJdbcTemplate,
            NamedParameterJdbcTemplate bridgeNamedParameterJdbcTemplate, NamedParameterJdbcTemplate deliveryNamedParameterJdbcTemplate) {
        this.integrationNamedParameterJdbcTemplate = integrationNamedParameterJdbcTemplate;
        this.campaignNamedParameterJdbcTemplate = campaignNamedParameterJdbcTemplate;
        this.bridgeNamedParameterJdbcTemplate = bridgeNamedParameterJdbcTemplate;
        this.deliveryNamedParameterJdbcTemplate = deliveryNamedParameterJdbcTemplate;
    }

    public NamedParameterJdbcTemplate getNamedParameterJdbcTemplate(@NonNull JdbcTemplateEnum templateEnum) {
        NamedParameterJdbcTemplate namedParameterJdbcTemplate = null;
        switch (templateEnum) {
            case BRIDGE:
                namedParameterJdbcTemplate = bridgeNamedParameterJdbcTemplate;
                break;
            case CAMPAIGN:
                namedParameterJdbcTemplate = campaignNamedParameterJdbcTemplate;
                break;
            case DELIVERY:
                namedParameterJdbcTemplate = deliveryNamedParameterJdbcTemplate;
                break;
            case INTEGRATION:
                namedParameterJdbcTemplate = integrationNamedParameterJdbcTemplate;
                break;
        }
        return namedParameterJdbcTemplate;
    }

}

package com.mediaocean.hackathon.virtualassistant.enums;

import lombok.NonNull;

public enum JdbcTemplateEnum {

    INTEGRATION, CAMPAIGN, DELIVERY, BRIDGE;

    public static JdbcTemplateEnum getJdbcTemplateEnum(@NonNull String templateName) {
        JdbcTemplateEnum templateEnum = null;
        for (JdbcTemplateEnum enumVal : JdbcTemplateEnum.values()) {
            if (enumVal.name().equalsIgnoreCase(templateName)) {
                templateEnum = enumVal;
                break;
            }
        }
        return templateEnum;
    }
}

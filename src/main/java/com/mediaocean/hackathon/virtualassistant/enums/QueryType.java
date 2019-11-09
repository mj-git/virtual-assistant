package com.mediaocean.hackathon.virtualassistant.enums;

import lombok.NonNull;

public enum QueryType {
    SELECT,UPDATE,DELETE;

    public static QueryType getQueryType(@NonNull String queryTypeName) {
        QueryType templateEnum = null;
        for (QueryType enumVal : QueryType.values()) {
            if (enumVal.name().equalsIgnoreCase(queryTypeName)) {
                templateEnum = enumVal;
                break;
            }
        }
        return templateEnum;
    }
}

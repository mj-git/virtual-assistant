package com.mediaocean.hackathon.virtualassistant.enums;

import lombok.NonNull;

public enum Action {

    DB_QUERY, CREATE_JIRA, PROMPT_USER, EXECUTE_ACTION;

    public static Action getAction(@NonNull String actionName) {
        Action action = null;
        for (Action enumVal : Action.values()) {
            if (enumVal.name().equalsIgnoreCase(actionName)) {
                action = enumVal;
                break;
            }
        }
        return action;
    }
}

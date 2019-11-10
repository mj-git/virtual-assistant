package com.mediaocean.hackathon.virtualassistant.dtos;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class JiraActionResponse {

    private String id;
    private String key;
    private String jiraUrl;
    private boolean result;
    private String error;
}

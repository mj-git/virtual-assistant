package com.mediaocean.hackathon.virtualassistant.dtos;

import com.fasterxml.jackson.databind.JsonNode;

import lombok.Data;

@Data
public class DatabaseQueryExecutionResponse {

    private String queryName;
    private JsonNode response;
    private boolean result;
}

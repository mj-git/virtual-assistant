package com.mediaocean.hackathon.virtualassistant.dtos;

import java.util.Map;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class DatabaseQueryExecutionRequest {

    private String queryName;
    private String query;
    private String dbName;
    private String queryType;
    private Map<String, String> queryParameters;

}

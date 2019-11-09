package com.mediaocean.hackathon.virtualassistant.service;

import com.mediaocean.hackathon.virtualassistant.config.JdbcTemplateFactory;
import com.mediaocean.hackathon.virtualassistant.config.NamedParameterJdbcTemplateFactory;
import com.mediaocean.hackathon.virtualassistant.dtos.DatabaseQueryExecutionRequest;
import com.mediaocean.hackathon.virtualassistant.dtos.DatabaseQueryExecutionResponse;
import com.mediaocean.hackathon.virtualassistant.enums.JdbcTemplateEnum;
import com.mediaocean.hackathon.virtualassistant.enums.QueryType;

import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;

import java.sql.ResultSetMetaData;

import lombok.NonNull;

@Service
public class DatabaseQueryExecutor implements ExecutionService<DatabaseQueryExecutionRequest, DatabaseQueryExecutionResponse> {

    public static final String RESULT = "result";
    private final JdbcTemplateFactory jdbcTemplateFactory;

    private final NamedParameterJdbcTemplateFactory namedParameterJdbcTemplateFactory;

    @Autowired
    public DatabaseQueryExecutor(JdbcTemplateFactory jdbcTemplateFactory, NamedParameterJdbcTemplateFactory namedParameterJdbcTemplateFactory) {
        this.jdbcTemplateFactory = jdbcTemplateFactory;
        this.namedParameterJdbcTemplateFactory = namedParameterJdbcTemplateFactory;
    }

    @Override
    public DatabaseQueryExecutionResponse execute(@NonNull DatabaseQueryExecutionRequest request) {
        DatabaseQueryExecutionResponse executionResponse = new DatabaseQueryExecutionResponse();
        QueryType queryType = QueryType.getQueryType(request.getQueryType());
        JdbcTemplateEnum templateEnum = JdbcTemplateEnum.getJdbcTemplateEnum(request.getDbName());
        NamedParameterJdbcTemplate namedParameterJdbcTemplate = namedParameterJdbcTemplateFactory.getNamedParameterJdbcTemplate(templateEnum);
        switch (queryType) {

            case SELECT:
                ObjectNode response = JsonNodeFactory.instance.objectNode();
                ArrayNode arrayNode = response.arrayNode();
                response.putPOJO(RESULT, arrayNode);
                executionResponse.setResult(true);
                executionResponse.setResponse(response);
                executionResponse.setQueryName(request.getQueryName());
                namedParameterJdbcTemplate.query(request.getQuery(), request.getQueryParameters(), resultSet -> {
                    ObjectNode result = JsonNodeFactory.instance.objectNode();
                    ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
                    for (int i = 1; i <= resultSetMetaData.getColumnCount(); i++) {
                        String columnName = resultSetMetaData.getColumnName(i);
                        String value = resultSet.getString(columnName);
                        result.put(columnName, value);
                    }
                    arrayNode.add(result);
                });
                break;

            case UPDATE:
                break;

            case DELETE:
                break;
        }

        return executionResponse;
    }

}

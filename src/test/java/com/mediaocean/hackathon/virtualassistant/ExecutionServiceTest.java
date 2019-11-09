package com.mediaocean.hackathon.virtualassistant;

import static com.mediaocean.hackathon.virtualassistant.service.DatabaseQueryExecutor.RESULT;

import com.mediaocean.hackathon.virtualassistant.dtos.DatabaseQueryExecutionRequest;
import com.mediaocean.hackathon.virtualassistant.dtos.DatabaseQueryExecutionResponse;
import com.mediaocean.hackathon.virtualassistant.service.ExecutionService;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.PropertySource;
import org.springframework.util.Assert;

import java.util.HashMap;
import java.util.Map;

@SpringBootTest
@PropertySource("classpath:application.properties")
public class ExecutionServiceTest {

    @Autowired
    private ExecutionService<DatabaseQueryExecutionRequest, DatabaseQueryExecutionResponse> executionService;

    @Test
    public void testSelectClauseWithStar() {
        DatabaseQueryExecutionRequest request = new DatabaseQueryExecutionRequest();
        request.setDbName("Integration");
        request.setQuery("select * From supplier_master");
        request.setQueryName("ALL_SUPPLIER_MASTER");
        request.setQueryType("Select");
        DatabaseQueryExecutionResponse response = executionService.execute(request);
        Assert.isTrue(response.isResult(), "Select All Result is True");
        Assert.notNull(response.getResponse().get(RESULT), "Multiple Rows are selected");
    }

    @Test
    public void testSelectClauseWithWhereClause() {
        DatabaseQueryExecutionRequest request = new DatabaseQueryExecutionRequest();
        request.setDbName("Integration");
        request.setQuery(" select * From supplier_master where id = :id");
        request.setQueryName("ALL_SUPPLIER_MASTER");
        request.setQueryType("Select");
        Map<String,String> parameter = new HashMap<>();
        parameter.put("id","150042276");
        request.setQueryParameters(parameter);
        DatabaseQueryExecutionResponse response = executionService.execute(request);
        Assert.isTrue(response.isResult(), "Select One Result is True");
        Assert.notNull(response.getResponse().get(RESULT), "Single Row selected");
    }

    @Test
    public void testSelectClauseWithWhereClauseWithSpecificColumns() {
        DatabaseQueryExecutionRequest request = new DatabaseQueryExecutionRequest();
        request.setDbName("Integration");
        request.setQuery("select id,supplier_name From supplier_master");
        request.setQueryName("ALL_SUPPLIER_MASTER");
        request.setQueryType("Select");
        DatabaseQueryExecutionResponse response = executionService.execute(request);
        Assert.isTrue(response.isResult(), "Select One Result is True");
        Assert.notNull(response.getResponse().get(RESULT), "Single Row selected");
    }
}

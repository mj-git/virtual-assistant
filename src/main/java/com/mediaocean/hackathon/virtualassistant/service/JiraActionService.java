package com.mediaocean.hackathon.virtualassistant.service;

import com.mediaocean.hackathon.virtualassistant.dtos.JiraActionRequest;
import com.mediaocean.hackathon.virtualassistant.dtos.JiraActionResponse;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;

@Service
public class JiraActionService implements ExecutionService<JiraActionRequest, JiraActionResponse> {

    @Value("${jira.authorization.base64.credentials}") //'Authorization: Basic '
    private String jiraAuthorizationBase64Encoded;

    @Value("${jira.url}")
    private String jiraUrl;

    private final RestTemplate restTemplate;

    private final JsonNodeFactory jsonNodeFactory;

    @Autowired
    public JiraActionService(RestTemplate restTemplate, JsonNodeFactory jsonNodeFactory) {
        this.restTemplate = restTemplate;
        this.jsonNodeFactory = jsonNodeFactory;
    }

    @Override
    public JiraActionResponse execute(JiraActionRequest jiraActionRequest) {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        headers.setBasicAuth(jiraAuthorizationBase64Encoded);
        HttpEntity<JsonNode> entity = new HttpEntity<>(createJsonRequest(jiraActionRequest), headers);
        ResponseEntity<JiraActionResponse> result = restTemplate.exchange(jiraUrl, HttpMethod.POST, entity, JiraActionResponse.class);
        return result.getStatusCode() == HttpStatus.OK ? result.getBody() : createFailedResponse();
    }

    private JiraActionResponse createFailedResponse() {
        JiraActionResponse response = new JiraActionResponse();
        response.setError("Error Occurred while Creating the Jira");
        response.setResult(false);
        return response;
    }

    private JsonNode createJsonRequest(JiraActionRequest jiraActionRequest) {
        ObjectNode requestNode = jsonNodeFactory.objectNode();
        ObjectNode fields = requestNode.putObject("fields");

        //create project node
        ObjectNode projectNode = fields.putObject("project");
        projectNode.put("key", jiraActionRequest.getProjectName());

        // summary of the ticket
        fields.put("summary", jiraActionRequest.getSummary());
        // description of the ticket
        fields.put("description", String.join("\n", jiraActionRequest.getDescription()));

        //issue type
        ObjectNode issueTypeNode = fields.putObject("issuetype");
        issueTypeNode.put("name", jiraActionRequest.getIssueType());

        //time tracking
        ObjectNode timeTrackingNode = fields.putObject("timetracking");
        timeTrackingNode.put("originalEstimate" , jiraActionRequest.getOriginalEstimate());
        timeTrackingNode.put("remainingEstimate" , jiraActionRequest.getRemainingEstimate());

        return requestNode;
    }
}

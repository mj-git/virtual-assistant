package com.mediaocean.hackathon.virtualassistant.dtos;

import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class JiraActionRequest {

    private String projectName;
    private String parentTask;
    private String summary;
    private List<String> description;
    private String issueType;
    private String originalEstimate;
    private String remainingEstimate;
}

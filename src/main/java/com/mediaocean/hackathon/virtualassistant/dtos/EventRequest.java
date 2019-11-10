package com.mediaocean.hackathon.virtualassistant.dtos;

import com.fasterxml.jackson.databind.JsonNode;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@ToString
public class EventRequest {

    private String eventId;
    private JsonNode requestPayload;
}

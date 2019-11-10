package com.mediaocean.hackathon.virtualassistant.dtos;

import com.fasterxml.jackson.databind.JsonNode;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class EventRequest {

    private String eventId;
    private JsonNode requestPayload;
}

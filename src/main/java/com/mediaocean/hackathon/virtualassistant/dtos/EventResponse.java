package com.mediaocean.hackathon.virtualassistant.dtos;

import com.fasterxml.jackson.databind.JsonNode;

import java.util.UUID;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class EventResponse {

    private JsonNode response;
    private UUID uuId;
    private int step;
}

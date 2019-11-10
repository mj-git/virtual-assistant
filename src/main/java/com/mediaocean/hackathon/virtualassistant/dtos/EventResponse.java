package com.mediaocean.hackathon.virtualassistant.dtos;

import com.fasterxml.jackson.databind.JsonNode;

import java.util.UUID;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@ToString
public class EventResponse {

    private JsonNode payload;
    private UUID uuId;
    private int step;
}

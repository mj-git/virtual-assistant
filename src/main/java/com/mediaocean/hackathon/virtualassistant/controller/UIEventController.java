package com.mediaocean.hackathon.virtualassistant.controller;

import com.mediaocean.hackathon.virtualassistant.dtos.EventRequest;
import com.mediaocean.hackathon.virtualassistant.dtos.EventResponse;

import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import java.util.UUID;

@Controller
public class UIEventController {

    private static final Logger LOGGER = LoggerFactory.getLogger(UIEventController.class);

    @Value("message.destination.prefix")
    private String messageDestinationPrefix;

    @MessageMapping("/chat")
    @SendTo("/topic/messages")
    public EventResponse eventHandler(EventRequest eventRequest) {
        LOGGER.info("Web Socket Request Received:{}", eventRequest);
        EventResponse response = new EventResponse();
        ObjectNode objectNode = JsonNodeFactory.instance.objectNode();
        objectNode.put("greetings", "HelloWorld");
        response.setPayload(objectNode);
        response.setStep(1);
        response.setUuId(UUID.randomUUID());
        LOGGER.info("Web Socket Response Send:{}", response);
        return response;
    }
}

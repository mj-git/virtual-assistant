package com.mediaocean.hackathon.virtualassistant.controller;

import com.mediaocean.hackathon.virtualassistant.dtos.EventRequest;
import com.mediaocean.hackathon.virtualassistant.dtos.EventResponse;

import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import java.util.UUID;

@Controller
public class UIEventController {


    @Value("message.destination.prefix")
    private String messageDestinationPrefix;

    @MessageMapping("/handleEvent")
    @SendTo("#{messageDestinationPrefix}/eventHandler")
    public EventResponse eventHandler(EventRequest eventRequest) {
        EventResponse response = new EventResponse();
        ObjectNode objectNode = JsonNodeFactory.instance.objectNode();
        objectNode.put("greetings", "HelloWorld");
        response.setResponse(objectNode);
        response.setStep(1);
        response.setUuId(UUID.randomUUID());
        return response;
    }
}

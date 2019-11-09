package com.mediaocean.hackathon.virtualassistant.service;

public interface ExecutionService<I,T> {

    T execute(I i);
}

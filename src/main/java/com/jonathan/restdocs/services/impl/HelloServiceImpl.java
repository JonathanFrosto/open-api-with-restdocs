package com.jonathan.restdocs.services.impl;

import com.jonathan.restdocs.dtos.MessageDTO;
import com.jonathan.restdocs.services.HelloService;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class HelloServiceImpl implements HelloService {

    private final Map<Long, String> messages = new HashMap<>();

    public HelloServiceImpl() {
        messages.put(1L, "First message");
        messages.put(2L, "Second message");
        messages.put(3L, "Third message");
    }

    @Override
    public MessageDTO getMessage(Long messageId) {
        String message = messages.get(messageId);

        MessageDTO messageDTO = new MessageDTO();
        messageDTO.setMessage(message);

        return messageDTO;
    }
}

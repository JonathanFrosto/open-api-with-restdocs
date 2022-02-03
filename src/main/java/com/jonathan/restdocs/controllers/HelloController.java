package com.jonathan.restdocs.controllers;

import com.jonathan.restdocs.dtos.MessageDTO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping(value = "/ola")
    public MessageDTO ola() {
        MessageDTO messageDTO = new MessageDTO();
        messageDTO.setMessage("Ola mundo");

        return messageDTO;
    }
}

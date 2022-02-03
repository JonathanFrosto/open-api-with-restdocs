package com.jonathan.restdocs.controllers;

import com.jonathan.restdocs.dtos.MessageDTO;
import com.jonathan.restdocs.services.HelloService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class HelloController {

    private HelloService service;

    public HelloController(HelloService service) {
        this.service = service;
    }

    @GetMapping(value = "/hello/{messageId}")
    public ResponseEntity<MessageDTO> hello(@PathVariable Long messageId) {
        return ResponseEntity.of(Optional.ofNullable(service.getMessage(messageId)));
    }
}

package com.jonathan.restdocs.services;

import com.jonathan.restdocs.dtos.MessageDTO;

public interface HelloService {

    MessageDTO getMessage(Long messageId);
}

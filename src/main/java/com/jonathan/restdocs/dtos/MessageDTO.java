package com.jonathan.restdocs.dtos;

public class MessageDTO {

    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public MessageDTO message(String message) {
        this.message = message;
        return this;
    }
}

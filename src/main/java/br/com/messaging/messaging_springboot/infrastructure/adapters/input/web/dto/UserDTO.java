package br.com.messaging.messaging_springboot.infrastructure.adapters.input.web.dto;

public record UserDTO (

    String name,
    Integer age,
    String email
)
{}

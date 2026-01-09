package br.com.messaging.messaging_springboot.infrastructure.adapters.output.persistence;

import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;

@Entity
@Table(name = "tb_users")
@Data // Gera Getters/Setters automaticos
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private String name;
    private int age;
    private String email;

    public UserEntity() {}

    public UserEntity(String name, String email, int age) {
        this.name = name;
       this.email = email;
       this.age = age;
    }

}
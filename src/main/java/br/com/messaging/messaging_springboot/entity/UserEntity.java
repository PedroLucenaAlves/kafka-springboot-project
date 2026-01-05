package br.com.messaging.messaging_springboot.entity;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "tb_users")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private String name;
    private int age;
    private String email;

    public UserEntity() {}

    public UserEntity(String name, int age, String email) {
        this.name = name;
        this.age = age;
        this.email = email;
    }

    public UUID getId() { return id; }
    public String getName() { return name; }
    public int getAge() { return age; }
    public String getEmail() { return email; }
}

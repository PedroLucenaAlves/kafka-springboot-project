package br.com.messaging.messaging_springboot.domain;

public class User {

    private String name;
    private int age;
    private String email;

    //Nao pode criar user sem um nome
    public User(String name, String email, int age) {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("Nome não pode ser vazio (Regra de Negócio!)");
        }
        this.name = name;
        this.email = email;
        this.age = age;
    }

    public String getName() { return name; }
    public String getEmail() { return email; }
    public int getAge() { return age; }
}

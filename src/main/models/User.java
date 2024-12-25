package main.models;

import java.util.UUID;

public class User {
    private UUID userId;
    private String name;

    public User(String name) {
        this.userId = UUID.randomUUID();
        this.name = name;
    }

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

package com.giftgalaxy.responses;

import jakarta.persistence.Column;

public class UserResponse {
    private int id;
    private String username;
    private String type;

    public UserResponse(int id, String username, String type) {
        this.id = id;
        this.username = username;
        this.type = type;
    }

    public UserResponse() {}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}

package com.giftgalaxy.Model.User;

import jakarta.persistence.MappedSuperclass;

@MappedSuperclass
public class NormalUser extends User {
    public NormalUser(String userName, String password) {
        super(userName, password, "NormalUser");
    }
}

package com.giftgalaxy.Model.User;

import jakarta.persistence.MappedSuperclass;

@MappedSuperclass
public class Administrator extends User {
    public Administrator(String userName, String password) {
        super(userName, password, "Administrator");
    }
}

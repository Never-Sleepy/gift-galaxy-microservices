package com.giftgalaxy.Model.User;

import jakarta.persistence.MappedSuperclass;

@MappedSuperclass
public class Administrator extends User {
    public Administrator(Long ID, String userName, String password) {
        super(ID, userName, password, "Administrator");
    }
}

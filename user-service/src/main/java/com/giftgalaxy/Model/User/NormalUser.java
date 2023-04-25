package com.giftgalaxy.Model.User;

import jakarta.persistence.MappedSuperclass;

@MappedSuperclass
public class NormalUser extends User {
    public NormalUser(Long ID, String userName, String password) {
        super(ID, userName, password, "NormalUser");
    }

}

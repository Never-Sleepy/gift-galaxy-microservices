package com.giftgalaxy.Database;

import com.giftgalaxy.Model.User.normalUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface userRepository extends JpaRepository<normalUser, Integer> {
    normalUser findBy(String username);
}

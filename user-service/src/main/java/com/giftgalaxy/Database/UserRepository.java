package com.giftgalaxy.Database;

import com.giftgalaxy.Model.User.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
    User findByUsername(String username);
    User findById(Long id);
}

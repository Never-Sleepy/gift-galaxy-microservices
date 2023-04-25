package com.giftgalaxy.Controller;

import com.giftgalaxy.Model.User.User;
import org.springframework.data.jpa.domain.Specification;

public class UserSpecification {
    public static Specification<User> isNotDeleted() {
        return (root, query, cb) -> cb.isNull(root.get("deletedAt"));
    }

    public static Specification<User> getById(Long id) {
        return (root, query, cb) -> cb.equal(root.get("id"), id);
    }
}

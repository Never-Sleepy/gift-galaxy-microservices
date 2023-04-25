package com.giftgalaxy.specifications;

import com.giftgalaxy.models.Recommendation;
import org.springframework.data.jpa.domain.Specification;

public class RecommendationSpecifications {
    public static Specification<Recommendation> isNotDeleted() {
        return (root, query, cb) -> cb.isNull(root.get("deletedAt"));
    }

    public static Specification<Recommendation> getById(Long id) {
        return (root, query, cb) -> cb.equal(root.get("id"), id);
    }
}

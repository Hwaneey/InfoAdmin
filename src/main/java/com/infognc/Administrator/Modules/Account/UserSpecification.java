package com.infognc.Administrator.Modules.Account;

import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class UserSpecification {
    public static Specification<Account> searchUser(Map<String, Object> filter) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList();

            filter.forEach((key, value) -> {
                String likeValue = "%" + value + "%";

                switch (key) {
                    case "status":
                        predicates.add(criteriaBuilder.like(root.get(key).as(String.class), likeValue));
                        break;
                    case "agentNum":
                        predicates.add(criteriaBuilder.like(root.get(key).as(String.class), likeValue));
                        break;
                    case "agentName":
                        predicates.add(criteriaBuilder.like(root.get(key).as(String.class), likeValue));
                        break;
                    case "agentCallNum":
                        predicates.add(criteriaBuilder.like(root.get(key).as(String.class), likeValue));
                        break;
                    case "part":
                        predicates.add(criteriaBuilder.like(root.get(key).as(String.class), likeValue));
                        break;
                    case "level":
                        predicates.add(criteriaBuilder.like(root.get(key).as(String.class), likeValue));
                        break;

                }
            });
            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }
}

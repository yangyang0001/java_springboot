package com.deepblue.inaction_00_spring.chapter_09.example_002;

import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 */
public class UserService {

    @Transactional(isolation = Isolation.REPEATABLE_READ, propagation = Propagation.REQUIRED)
    public void addUser() {

    }
}

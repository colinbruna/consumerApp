package com.brunas.consumerApp.service;

import com.brunas.consumerApp.model.User;
import com.brunas.consumerApp.repository.UserRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class UserService {

    private final UserRepository repository;

    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public User create(User user) {
        return repository.save(user);
    }
}

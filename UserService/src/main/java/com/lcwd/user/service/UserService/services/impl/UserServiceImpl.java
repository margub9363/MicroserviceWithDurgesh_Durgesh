package com.lcwd.user.service.UserService.services.impl;

import com.lcwd.user.service.UserService.Exceptions.ResourceNotFoundException;
import com.lcwd.user.service.UserService.entities.User;
import com.lcwd.user.service.UserService.repositories.UserRepository;
import com.lcwd.user.service.UserService.services.UserServices;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@Slf4j
public class UserServiceImpl implements UserServices {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public User saveUser(User user) {
        String randomUserId = UUID.randomUUID().toString();
        user.setUserId(randomUserId);
        return userRepository.save(user);
    }

    @Override
    public List<User> getAllUser() {
        return userRepository.findAll();
    }

    @Override
    public User getUser(String userId) {
//        get user from database from the user database repository
        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User with given Id is not found on server " + userId));
//        fetch rating for the above user from RATING-SERVICE
//          http://localhost:8083/ratings/user/91457258-82c8-4a63-abcd-d1e135e82d33
        ArrayList forObject = restTemplate.getForObject("http://localhost:8083/ratings/user/91457258-82c8-4a63-abcd-d1e135e82d33", ArrayList.class);
        log.info("{}",forObject);


        return user;
    }
}

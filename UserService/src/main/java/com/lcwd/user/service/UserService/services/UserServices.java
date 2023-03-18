package com.lcwd.user.service.UserService.services;

import com.lcwd.user.service.UserService.entities.User;

import java.util.List;

public interface UserServices {

//    user operations

    //    create
    User saveUser(User user);

//    get all user
    List<User> getAllUser();

//    get Single user of given UserId
    User getUser(String userId);

//    Todo: delete
//    Todo: update
}

package com.lcwd.user.service.UserService.services.impl;

import com.lcwd.user.service.UserService.Exceptions.ResourceNotFoundException;
import com.lcwd.user.service.UserService.entities.Hotel;
import com.lcwd.user.service.UserService.entities.Rating;
import com.lcwd.user.service.UserService.entities.User;
import com.lcwd.user.service.UserService.external.services.HotelService;
import com.lcwd.user.service.UserService.repositories.UserRepository;
import com.lcwd.user.service.UserService.services.UserServices;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Slf4j
public class UserServiceImpl implements UserServices {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    HotelService hotelService;

    @Override
    public User saveUser(User user) {
        String randomUserId = UUID.randomUUID().toString();
        user.setUserId(randomUserId);
        return userRepository.save(user);
    }

    @Override
    public List<User> getAllUser() {
//
        return userRepository.findAll();
    }

    @Override
    public User getUser(String userId) {
//        get user from database from the user database repository
        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User with given Id is not found on server " + userId));
//        fetch rating for the above user from RATING-SERVICE
//          http://localhost:8083/ratings/user/91457258-82c8-4a63-abcd-d1e135e82d33
        Rating[] ratingsOfUser = restTemplate.getForObject("http://RATING-SERVICE/ratings/user/"+user.getUserId(), Rating[].class);
        log.info("{}",ratingsOfUser);

        List<Rating> ratings = Arrays.stream(ratingsOfUser).toList();

        List<Rating> ratingList = ratings.stream().map(rating -> {
//            api call to hotel service to get the hotel
//            http://localhost:8082/hotels/ce4206a1-4eef-4a7a-b76e-29eca1062114
//            ResponseEntity<Hotel> forEntity = restTemplate.getForEntity("http://HOTEL-SERVICE/hotels/"+rating.getHotelId(), Hotel.class);
            Hotel hotel = hotelService.getHotel(rating.getHotelId());
            log.info(hotel.toString());
//            log.info("response status code: {}",forEntity.getStatusCode());
//            set the hotel to rating
            rating.setHotel(hotel);
//            return the rating
            return rating;
        }).collect(Collectors.toList());
        user.setRatings(ratingList);

        return user;
    }
}

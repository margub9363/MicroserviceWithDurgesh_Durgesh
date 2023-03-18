package com.lcwd.rating.services;

import com.lcwd.rating.entities.Rating;

import java.util.List;


public interface RatingService {

//    create
    Rating create(Rating rating);

//    getAllRatings
    List<Rating> getRatings();

//    user wise rating
    List<Rating> getRatingByUserId(String userId);

//    hotel wise rating
    List<Rating> getRatingByHotelId(String hotelId);

}

package com.lcwd.rating.controllers;

import com.lcwd.rating.entities.Rating;
import com.lcwd.rating.services.RatingService;
import com.lcwd.rating.services.impl.RatingServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ratings")
public class RatingController {

    @Autowired
    private RatingServiceImpl ratingService;

    @PostMapping()
    public ResponseEntity<Rating> create(@RequestBody Rating rating){
        Rating rating1 = ratingService.create(rating);
        return ResponseEntity.status(HttpStatus.CREATED).body(rating1);
    }

    @GetMapping()
    public ResponseEntity<List<Rating>> getRatings(){
        return ResponseEntity.ok(ratingService.getRatings());
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Rating>> getRatingByUserId(@PathVariable String userId){
        return ResponseEntity.ok(ratingService.getRatingByUserId(userId));
    }

    @GetMapping("/hotels/{hotelId}")
    public ResponseEntity<List<Rating>> getRatingByHotelId(@PathVariable String hotelId){
        return ResponseEntity.ok(ratingService.getRatingByHotelId(hotelId));
    }
}

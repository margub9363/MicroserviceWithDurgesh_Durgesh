package com.lcwd.hotel.HotelService.services;

import com.lcwd.hotel.HotelService.entities.Hotel;

import java.util.List;
import java.util.Optional;

public interface HotelService {


//    create
    Hotel createHotel(Hotel hotel);

//    getall
    List<Hotel> getHotels();

//    getSingle
    Optional<Hotel> getHotel(String id);

}

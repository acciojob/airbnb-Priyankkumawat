package com.driver.repository;

import com.driver.model.Booking;
import com.driver.model.Hotel;
import com.driver.model.User;
import io.swagger.models.auth.In;

import java.util.*;

public class HotelManagementRepository {
    private Map<String, Booking> bookingMap=new HashMap<>();
    private Map<Integer, User> userMap=new HashMap<>();
    private Map<String, Hotel> hotelMap=new HashMap<>();
    public void addHotel(Hotel hotel){
        hotelMap.put(hotel.getHotelName(),hotel);
    }
    public Hotel getHotel(String name){
        return hotelMap.get(name);
    }
    public Collection<Hotel> getHotelMapValues(){
        return hotelMap.values();
    }
    public Integer addUser(User user){
        userMap.put(user.getaadharCardNo(),user);
        return user.getaadharCardNo();
    }
    public void addBooking(Booking booking){
        bookingMap.put(booking.getBookingId(),booking);
    }
    public Collection<Booking> getBooking(){return bookingMap.values();}
}
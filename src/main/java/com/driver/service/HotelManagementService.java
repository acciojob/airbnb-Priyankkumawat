package com.driver.service;

import com.driver.model.Booking;
import com.driver.model.Facility;
import com.driver.model.Hotel;
import com.driver.model.User;
import com.driver.repository.HotelManagementRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.UUID;

public class HotelManagementService {
    @Autowired
    HotelManagementRepository hotelManagementRepository;
    public String addHotel(Hotel hotel){
        if(hotel.getHotelName() == "" || hotel == null) return "FAILURE";
        for(Hotel hotel1 : hotelManagementRepository.getHotelMapValues()){
            if(hotel.getHotelName()==hotel.getHotelName()) return "FAILURE";
        }
        hotelManagementRepository.addHotel(hotel);
        return "SUCCESS";
    }
    public Integer addUser(User user){
        return hotelManagementRepository.addUser(user);
    }
    public String hotelWithMostFacility(){
        int f=0;
        String ans="";
        for(Hotel hotel : hotelManagementRepository.getHotelMapValues()){
            if(hotel.getFacilities().size() > f){
                f=hotel.getFacilities().size();
                ans=hotel.getHotelName();
            }
        }
        return ans;
    }
    public int bookHotel(Booking booking){
        String uuid=UUID.randomUUID().toString();
        booking.setBookingId(uuid);
        Hotel hotel=hotelManagementRepository.getHotel(booking.getHotelName());
        if(hotel.getAvailableRooms() < booking.getNoOfRooms()){
            return -1;
        }
        int amount=hotel.getPricePerNight()*booking.getNoOfRooms();
        booking.setAmountToBePaid(amount);
        hotelManagementRepository.addBooking(booking);
        return amount;
    }
    public int getBookingByPerson(int aadhar){
        int cnt=0;
        for(Booking booking : hotelManagementRepository.getBooking()){
            if(booking.getBookingAadharCard() == aadhar) cnt++;
        }
        return  cnt;
    }
    public Hotel updateFacilities(List<Facility> facilitiesNew,String name){
        Hotel hotel=hotelManagementRepository.getHotel(name);
        List<Facility> facilitiesOld=hotel.getFacilities();
        for(Facility facility : facilitiesNew){
            boolean flg=true;
            for (Facility facility1 : facilitiesOld){
                if(facility == facility1){
                    flg=false;
                }
            }
            if(flg){
                facilitiesOld.add(facility);
            }
        }
        hotel.setFacilities(facilitiesOld);
        hotelManagementRepository.addHotel(hotel);
        return hotel;
    }
}
package com.driver.Repository;

import com.driver.model.Booking;
import com.driver.model.Hotel;
import com.driver.model.User;
import com.driver.utilities.Utilities;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Optional;
import java.util.Random;

@Repository
public class HotelManagementRepository {
    private HashMap<String, Hotel> hotelMap = new HashMap<>();
    private HashMap<Integer, User> userMap = new HashMap<>();

    private HashMap<String, Booking> bookingMap = new HashMap<>();

    public Optional<Hotel> getHotelByName(String name){
        if(hotelMap.containsKey(name)){
            return Optional.of(hotelMap.get(name));
        }
        return Optional.empty();
    }

    public void addHotel(Hotel hotel){
        hotelMap.put(hotel.getHotelName(),hotel);
    }

    public void addUser(User user){
        userMap.put(user.getaadharCardNo(),user);
    }

    public Optional<User> getUserByAadhar(Integer aadharNo){
        if(userMap.containsKey(aadharNo)){
            return Optional.of(userMap.get(aadharNo));
        }

        return Optional.empty();
    }

    public String getHotelWithMostFacilities() {
        int max = Integer.MIN_VALUE;
        String ansHotel = "";
        for(String name: hotelMap.keySet()){
            Hotel hotel = hotelMap.get(name);
            int numberOfFacilities = hotel.getFacilities().size();
            if(numberOfFacilities>max){
                max = Math.max(numberOfFacilities,max);
                ansHotel = hotel.getHotelName();
            }
            if(numberOfFacilities==max){
                ansHotel = Utilities.compareString(ansHotel,hotel.getHotelName());
            }
        }

        return ansHotel;
    }

    public void bookRoom(Booking booking) {
        Random random = new Random();
        String UUID = Utilities.generateId();
        while(bookingMap.containsKey(UUID)){
            UUID = Utilities.generateId();
        }
        if(!bookingMap.containsKey(UUID)) bookingMap.put(UUID,booking);
    }

    public Optional<Booking> getBookingByUUID(Integer UUID){
        if(bookingMap.containsKey(UUID)){
            return Optional.of(bookingMap.get(UUID));
        }

        return Optional.empty();
    }

    public int getNumberOfBookingsForAadhar(Integer aadharCard) {
        int count = 0;
        for(Booking booking: bookingMap.values()){
            if(booking.getBookingAadharCard()==aadharCard) count++;
        }
        return count;
    }
}

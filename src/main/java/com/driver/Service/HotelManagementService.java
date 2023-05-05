package com.driver.Service;

import com.driver.Repository.HotelManagementRepository;
import com.driver.model.Booking;
import com.driver.model.Facility;
import com.driver.model.Hotel;
import com.driver.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
public class HotelManagementService {


    HotelManagementRepository repository = new HotelManagementRepository();

    public String addHotel(Hotel hotel) throws RuntimeException{
        Optional<Hotel> opt = repository.getHotelByName(hotel.getHotelName());
        if(opt.isPresent()) return "FAILURE";
        repository.addHotel(hotel);
        return "SUCCESS";
    }

    public Integer addUser(User user) {
        repository.addUser(user);
        Optional<User> opt = repository.getUserByAadhar(user.getaadharCardNo());
        return opt.get().getaadharCardNo();
    }

    public String getHotelWithMostFacilities() {
        return repository.getHotelWithMostFacilities();
    }

    public int bookRoom(Booking booking) {
        Integer noOFRooms = booking.getNoOfRooms();
        String hotelName = booking.getHotelName();

        Optional<Hotel> hotel = repository.getHotelByName(hotelName);
        //if number of rooms are not available
        if(hotel.isPresent()){
            if(noOFRooms>hotel.get().getAvailableRooms()){
                return -1;
            }
            return noOFRooms*hotel.get().getPricePerNight();
        }
        return booking.getAmountToBePaid();
    }

    public int getBookings(Integer aadharCard) {
        return repository.getNumberOfBookingsForAadhar(aadharCard);
    }

    public Hotel updateFacilities(List<Facility> newFacilities, String hotelName) {
        Optional<Hotel> opt = repository.getHotelByName(hotelName);
        if(opt.get().getFacilities().size()< newFacilities.size()){
            opt.get().setFacilities(newFacilities);
            repository.addHotel(opt.get());
        }
        return opt.get();
    }
}

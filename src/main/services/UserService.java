package main.services;

import main.models.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class UserService {
    BookingService bookingService;
    private Map<UUID, User> uuidUserMap;

    public UserService(BookingService bookingService) {
        this.bookingService = bookingService;
        this.uuidUserMap = new HashMap<>();
    }

    public Map<UUID, User> getUuidUserMap() {
        return uuidUserMap;
    }

    public void setUuidUserMap(Map<UUID, User> uuidUserMap) {
        this.uuidUserMap = uuidUserMap;
    }

    public User createUser(String name) {
        User user = new User(name);
        this.uuidUserMap.put(user.getUserId(), user);
        this.bookingService.getUserBookingMap().put(user.getUserId(), new ArrayList<>());
        return user;
    }
}

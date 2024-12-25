package main.services;

import main.exceptions.*;
import main.models.Booking;
import main.models.BookingStatus;
import main.models.Show;
import main.models.Showseat;

import java.util.*;

public class BookingService {
    ShowService showService;
    private Map<UUID, Booking> uuidBookingMap;
    private Map<UUID, List<UUID>> userBookingMap;
    private Map<UUID, List<UUID>> showBookingMap;

    public BookingService( ShowService showService) {
        this.showService = showService;
        this.uuidBookingMap = new HashMap<>();
        this.userBookingMap = new HashMap<>();
        this.showBookingMap = new HashMap<>();
    }

    public Map<UUID, Booking> getUuidBookingMap() {
        return uuidBookingMap;
    }

    public void setUuidBookingMap(Map<UUID, Booking> uuidBookingMap) {
        this.uuidBookingMap = uuidBookingMap;
    }

    public Map<UUID, List<UUID>> getUserBookingMap() {
        return userBookingMap;
    }

    public void setUserBookingMap(Map<UUID, List<UUID>> userBookingMap) {
        this.userBookingMap = userBookingMap;
    }

    public Map<UUID, List<UUID>> getShowBookingMap() {
        return showBookingMap;
    }

    public void setShowBookingMap(Map<UUID, List<UUID>> showBookingMap) {
        this.showBookingMap = showBookingMap;
    }

    synchronized public Booking createBooking(UUID showId, UUID userId, List<UUID> showseatIdList) {
        if (!this.showService.getUuidShowMap().containsKey(showId)) {
            throw new ShowNotFoundException();
        }

        for (UUID showseatId : showseatIdList) {
            if (!this.showService.getUuidShowseatMap().containsKey(showseatId)) {
                throw new ShowseatNotFoundException();
            }
            if (!this.showService.getUuidShowseatMap().get(showseatId).isAvailable()) {
                UUID bookingId = this.showService.getUuidShowseatMap().get(showseatId).getBookingId();
                Booking existingBooking = this.uuidBookingMap.get(bookingId);
                if (!existingBooking.getBookingStatus().equals(BookingStatus.CONFIRMED) && new Date().getTime() - existingBooking.getBookingTime() > 100*1000) {
                    this.showService.getUuidShowseatMap().get(showseatId).setAvailable(true);
                } else {
                    throw new SeatNotAvailableException();
                }
            }
        }
        for (UUID showseatId : showseatIdList) {
            this.showService.getUuidShowseatMap().get(showseatId).setAvailable(false);
        }
        Booking booking = new Booking(showId, userId, showseatIdList);
        this.uuidBookingMap.put(booking.getBookingId(), booking);
        if (!this.userBookingMap.containsKey(userId)) {
            this.userBookingMap.put(userId, new ArrayList<>());
        }
        this.userBookingMap.get(userId).add(booking.getBookingId());
        if (!this.showBookingMap.containsKey(showId)) {
            this.showBookingMap.put(showId, new ArrayList<>());
        }
        this.showBookingMap.get(showId).add(booking.getBookingId());

        for (UUID showseatId : showseatIdList) {
            this.showService.getUuidShowseatMap().get(showseatId).setBookingId(booking.getBookingId());
        }
        return booking;
    }

    synchronized public void confirmBooking(UUID bookingId) {
        if (!this.uuidBookingMap.containsKey(bookingId)) {
            throw new BookingNotFoundException();
        }
        Booking booking = this.uuidBookingMap.get(bookingId);
        if (!booking.getBookingStatus().equals(BookingStatus.CREATED)) {
            throw new IncorrectBookingStatusException();
        }
        if (new Date().getTime() - booking.getBookingTime() > 100 * 1000) {
            throw new BookingCancelledException();
        }
        booking.setBookingStatus(BookingStatus.CONFIRMED);
        this.uuidBookingMap.put(bookingId, booking);
    }

    synchronized public void cancelBooking(UUID bookingId) {
        if (!this.uuidBookingMap.containsKey(bookingId)) {
            throw new BookingNotFoundException();
        }

        Booking booking = this.uuidBookingMap.get(bookingId);
        if (booking.getBookingStatus().equals(BookingStatus.CANCELLED)) {
            throw new IncorrectBookingStatusException();
        }
        List<UUID> showseatIdList = booking.getShowseatIdList();
        for (UUID showseatId : showseatIdList) {
            this.showService.getUuidShowseatMap().get(showseatId).setAvailable(true);
            this.showService.getUuidShowseatMap().get(showseatId).setBookingId(null);
        }
        booking.setBookingStatus(BookingStatus.CANCELLED);
    }
}

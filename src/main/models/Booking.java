package main.models;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public class Booking {
    private UUID bookingId;
    private UUID showId;
    private List<UUID> showseatIdList;
    private UUID bookedByUserId;
    private BookingStatus bookingStatus;
    private long bookingTime;

    public Booking(UUID showId, UUID bookedByUserId, List<UUID> showseatIdList) {
        this.bookingId = UUID.randomUUID();
        this.showId = showId;
        this.showseatIdList = showseatIdList;
        this.bookedByUserId = bookedByUserId;
        this.bookingStatus = BookingStatus.CREATED;
        this.bookingTime = new Date().getTime();
    }

    public UUID getBookingId() {
        return bookingId;
    }

    public void setBookingId(UUID bookingId) {
        this.bookingId = bookingId;
    }

    public UUID getShowId() {
        return showId;
    }

    public void setShowId(UUID showId) {
        this.showId = showId;
    }

    public List<UUID> getShowseatIdList() {
        return showseatIdList;
    }

    public void setShowseatIdList(List<UUID> showseatIdList) {
        this.showseatIdList = showseatIdList;
    }

    public UUID getBookedByUserId() {
        return bookedByUserId;
    }

    public void setBookedByUserId(UUID bookedByUserId) {
        this.bookedByUserId = bookedByUserId;
    }

    public BookingStatus getBookingStatus() {
        return bookingStatus;
    }

    public void setBookingStatus(BookingStatus bookingStatus) {
        this.bookingStatus = bookingStatus;
    }

    public long getBookingTime() {
        return bookingTime;
    }

    public void setBookingTime(long bookingTime) {
        this.bookingTime = bookingTime;
    }
}

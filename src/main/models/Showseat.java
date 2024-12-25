package main.models;

import java.util.UUID;

public class Showseat {
    private UUID showseatId;
    private UUID showId;
    private UUID seatId;
    private boolean isAvailable;
    private UUID bookingId;

    public Showseat(UUID showId, UUID seatId) {
        this.showseatId = UUID.randomUUID();
        this.showId = showId;
        this.seatId = seatId;
        this.isAvailable = true;
        this.bookingId = null;
    }

    public UUID getShowseatId() {
        return showseatId;
    }

    public void setShowseatId(UUID showseatId) {
        this.showseatId = showseatId;
    }

    public UUID getShowId() {
        return showId;
    }

    public void setShowId(UUID showId) {
        this.showId = showId;
    }

    public UUID getSeatId() {
        return seatId;
    }

    public void setSeatId(UUID seatId) {
        this.seatId = seatId;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    public UUID getBookingId() {
        return bookingId;
    }

    public void setBookingId(UUID bookingId) {
        this.bookingId = bookingId;
    }
}

package main.models;

import java.util.UUID;

public class Seat {
    private UUID seatId;

    public Seat() {
        this.seatId = UUID.randomUUID();
    }

    public UUID getSeatId() {
        return seatId;
    }

    public void setSeatId(UUID seatId) {
        this.seatId = seatId;
    }
}

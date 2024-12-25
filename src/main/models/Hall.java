package main.models;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Hall {
    private UUID hallId;
    private String name;
    private List<UUID> seatIdList;
    private UUID showId;

    public Hall(String name) {
        this.hallId = UUID.randomUUID();
        this.name = name;
        this.seatIdList = new ArrayList<>();
        this.showId = null;
    }

    public UUID getHallId() {
        return hallId;
    }

    public void setHallId(UUID hallId) {
        this.hallId = hallId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<UUID> getSeatIdList() {
        return seatIdList;
    }

    public void setSeatIdList(List<UUID> seatIdList) {
        this.seatIdList = seatIdList;
    }

    public UUID getShowId() {
        return showId;
    }

    public void setShowId(UUID showId) {
        this.showId = showId;
    }
}

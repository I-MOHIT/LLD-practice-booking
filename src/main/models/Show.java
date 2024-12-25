package main.models;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Show {
    private UUID showId;
    private String name;
    private UUID hallId;
    private List<UUID> showseatIdList;

    public Show(String name, UUID hallId) {
        this.showId = UUID.randomUUID();
        this.name = name;
        this.hallId = hallId;
        this.showseatIdList = new ArrayList<>();
    }

    public UUID getShowId() {
        return showId;
    }

    public void setShowId(UUID showId) {
        this.showId = showId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public UUID getHallId() {
        return hallId;
    }

    public void setHallId(UUID hallId) {
        this.hallId = hallId;
    }

    public List<UUID> getShowseatIdList() {
        return showseatIdList;
    }

    public void setShowseatIdList(List<UUID> showseatIdList) {
        this.showseatIdList = showseatIdList;
    }
}

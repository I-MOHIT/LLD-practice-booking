package main.models;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Theatre {
    private UUID theatreId;
    private String name;
    private List<UUID> hallIdList;

    public Theatre(String name) {
        this.theatreId = UUID.randomUUID();
        this.name = name;
        this.hallIdList = new ArrayList<>();
    }

    public UUID getTheatreId() {
        return theatreId;
    }

    public void setTheatreId(UUID theatreId) {
        this.theatreId = theatreId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<UUID> getHallIdList() {
        return hallIdList;
    }

    public void setHallIdList(List<UUID> hallIdList) {
        this.hallIdList = hallIdList;
    }
}

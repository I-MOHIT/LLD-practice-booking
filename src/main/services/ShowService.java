package main.services;

import main.exceptions.HallNotFoundException;
import main.exceptions.SeatNotFoundException;
import main.exceptions.ShowNotFoundException;
import main.models.Hall;
import main.models.Seat;
import main.models.Show;
import main.models.Showseat;

import java.util.*;

public class ShowService {
    TheatreService theatreService;
    private Map<UUID, Show> uuidShowMap;
    private Map<UUID, Showseat> uuidShowseatMap;
    private Map<String, UUID> nameShowMap;

    public ShowService(TheatreService theatreService) {
        this.theatreService = theatreService;
        this.uuidShowMap = new HashMap<>();
        this.uuidShowseatMap = new HashMap<>();
        this.nameShowMap = new HashMap<>();
    }

    public Map<UUID, Show> getUuidShowMap() {
        return uuidShowMap;
    }

    public void setUuidShowMap(Map<UUID, Show> uuidShowMap) {
        this.uuidShowMap = uuidShowMap;
    }

    public Map<UUID, Showseat> getUuidShowseatMap() {
        return uuidShowseatMap;
    }

    public void setUuidShowseatMap(Map<UUID, Showseat> uuidShowseatMap) {
        this.uuidShowseatMap = uuidShowseatMap;
    }

    public Map<String, UUID> getNameShowMap() {
        return nameShowMap;
    }

    public void setNameShowMap(Map<String, UUID> nameShowMap) {
        this.nameShowMap = nameShowMap;
    }

    public Show createShow(String name, UUID hallId) {
        if (!this.theatreService.getUuidHallMap().containsKey(hallId)) {
            throw new HallNotFoundException();
        }
        Show show = new Show(name, hallId);
        this.uuidShowMap.put(show.getShowId(), show);
        // Update the show in the hall details
        Hall hall = this.theatreService.getUuidHallMap().get(hallId);
        hall.setShowId(show.getShowId());
        this.theatreService.getUuidHallMap().put(hall.getHallId(), hall);
        this.nameShowMap.put(name, show.getShowId());

        return show;
    }

    public Showseat assignSeatToShow(UUID seatId, UUID showId) {
        if (!this.getUuidShowMap().containsKey(showId)) {
            throw new ShowNotFoundException();
        }
        // If the seat doesn't exist in the hall
        UUID hallId = this.getUuidShowMap().get(showId).getHallId();
        if (!this.theatreService.getUuidHallMap().get(hallId).getSeatIdList().contains(seatId)) {
            throw new SeatNotFoundException();
        }
        Showseat showseat = new Showseat(showId, seatId);
        this.uuidShowseatMap.put(showseat.getShowseatId(), showseat);
        // Update the showseatIdList for the show
        Show show = this.getUuidShowMap().get(showId);
        List<UUID> showseatIdList = show.getShowseatIdList();
        showseatIdList.add(showseat.getShowseatId());

        return showseat;
    }
}

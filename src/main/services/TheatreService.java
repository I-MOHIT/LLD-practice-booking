package main.services;

import main.exceptions.HallNotFoundException;
import main.exceptions.SeatNotFoundException;
import main.exceptions.TheatreNotFoundException;
import main.models.Hall;
import main.models.Seat;
import main.models.Theatre;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class TheatreService {
    private Map<UUID, Theatre> uuidTheatreMap;
    private Map<UUID, Hall> uuidHallMap;
    private Map<UUID, Seat> uuidSeatMap;
    private Map<String, UUID> nameTheatreMap;

    public TheatreService() {
        this.uuidTheatreMap = new HashMap<>();
        this.uuidHallMap = new HashMap<>();
        this.uuidSeatMap = new HashMap<>();
        this.nameTheatreMap = new HashMap<>();
    }

    public Map<UUID, Theatre> getUuidTheatreMap() {
        return uuidTheatreMap;
    }

    public void setUuidTheatreMap(Map<UUID, Theatre> uuidTheatreMap) {
        this.uuidTheatreMap = uuidTheatreMap;
    }

    public Map<UUID, Hall> getUuidHallMap() {
        return uuidHallMap;
    }

    public void setUuidHallMap(Map<UUID, Hall> uuidHallMap) {
        this.uuidHallMap = uuidHallMap;
    }

    public Map<UUID, Seat> getUuidSeatMap() {
        return uuidSeatMap;
    }

    public void setUuidSeatMap(Map<UUID, Seat> uuidSeatMap) {
        this.uuidSeatMap = uuidSeatMap;
    }

    public Map<String, UUID> getNameTheatreMap() {
        return nameTheatreMap;
    }

    public void setNameTheatreMap(Map<String, UUID> nameTheatreMap) {
        this.nameTheatreMap = nameTheatreMap;
    }

    public Theatre createTheatre(String name) {
        Theatre theatre = new Theatre(name);
        this.uuidTheatreMap.put(theatre.getTheatreId(), theatre);
        this.nameTheatreMap.put(name, theatre.getTheatreId());
        return theatre;
    }

    public Hall createHall(String name) {
        Hall hall = new Hall(name);
        this.uuidHallMap.put(hall.getHallId(), hall);
        return hall;
    }

    public Seat createSeat() {
        Seat seat = new Seat();
        this.uuidSeatMap.put(seat.getSeatId(), seat);
        return seat;
    }

    public void assignHallToTheatre(UUID hallId, UUID theatreId) {
        if (!uuidHallMap.containsKey(hallId)) {
            throw new HallNotFoundException();
        }
        if (!uuidTheatreMap.containsKey(theatreId)) {
            throw new TheatreNotFoundException();
        }
        Theatre theatre = this.uuidTheatreMap.get(theatreId);
        List<UUID> hallIdList = theatre.getHallIdList();
        if (!hallIdList.contains(hallId)) {
            hallIdList.add(hallId);
        }
        theatre.setHallIdList(hallIdList);
        this.uuidTheatreMap.put(theatreId, theatre);
    }

    public void assignSeatToHall(UUID seatId, UUID hallId) {
        if (!uuidSeatMap.containsKey(seatId)) {
            throw new SeatNotFoundException();
        }
        if (!uuidHallMap.containsKey(hallId)) {
            throw new HallNotFoundException();
        }
        Hall hall = this.uuidHallMap.get(hallId);
        List<UUID> seatIdList = hall.getSeatIdList();
        if (!seatIdList.contains(seatId)) {
            seatIdList.add(seatId);
        }
        hall.setSeatIdList(seatIdList);
        this.uuidHallMap.put(hallId, hall);
    }
}

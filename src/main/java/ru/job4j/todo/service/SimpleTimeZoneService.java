package ru.job4j.todo.service;

import org.springframework.stereotype.Service;
import ru.job4j.todo.dto.TimeZoneDTO;

import java.util.ArrayList;
import java.util.List;
import java.util.TimeZone;

@Service
public class SimpleTimeZoneService implements TimeZoneService {

    public List<TimeZoneDTO> getAllTimeZones() {
        TimeZone defTz = null;
        if (defTz == null) {
            defTz = TimeZone.getDefault();
        }

        List<TimeZoneDTO> zones = new ArrayList<>();
        for (String id : TimeZone.getAvailableIDs()) {
            TimeZone tz = TimeZone.getTimeZone(id);
            int offset = tz.getRawOffset();
            int hours = offset / (1000 * 60 * 60);
            int minutes = Math.abs((offset / (1000 * 60)) % 60);
            String offsetStr = String.format("GMT%+d:%02d", hours, minutes);
            zones.add(new TimeZoneDTO(id, tz.getDisplayName(), offsetStr));
        }
        return zones;
    }
}

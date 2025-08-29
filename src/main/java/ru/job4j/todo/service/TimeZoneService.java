package ru.job4j.todo.service;

import ru.job4j.todo.dto.TimeZoneDTO;

import java.util.List;

public interface TimeZoneService {
    List<TimeZoneDTO> getAllTimeZones();
}

package ru.job4j.todo.dto;

public class TimeZoneDTO {
    private String id;

    private String displayName;

    private String timeOffset;

    public TimeZoneDTO(String id, String displayName, String timeOffset) {
        this.id = id;
        this.displayName = displayName;
        this.timeOffset = timeOffset;
    }

    public String getId() {
        return id;
    }

    public String getDisplayName() {
        return displayName;
    }

    public String getTimeOffset() {
        return timeOffset;
    }
}

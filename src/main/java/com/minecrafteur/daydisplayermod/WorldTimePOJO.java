package com.minecrafteur.daydisplayermod;

public class WorldTimePOJO {
    private final long timeDay;
    private final long day;
    private final int dayTicks;
    private final int hours;
    private final int minutes;
    private final int seconds;

    WorldTimePOJO(long timeDay) {
        this.timeDay = timeDay;
        this.day = (int) (timeDay / 24000);
        this.dayTicks = (int) (timeDay % 24000);
        this.hours = ((dayTicks / 1000) + 6) % 24;
        this.minutes = (int) ((dayTicks % 1000) / 16.666666);
        this.seconds = (int) ((dayTicks % 16.666666) / 0.277777);
    }

    public long getTimeDay() {
        return timeDay;
    }

    public long getDay() {
        return day;
    }

    public int getDayTicks() {
        return dayTicks;
    }

    public int getHours() {
        return hours;
    }

    public int getMinutes() {
        return minutes;
    }

    public int getSeconds() {
        return seconds;
    }
}

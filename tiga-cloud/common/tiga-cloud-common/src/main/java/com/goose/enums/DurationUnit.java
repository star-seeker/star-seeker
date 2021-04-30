package com.goose.enums;

import java.time.Duration;

public enum DurationUnit {

    WEEK("Week", Duration.ofSeconds(7 * 86400L)),

    MONTH("Months", Duration.ofSeconds(31556952L / 12)),

    Quarter("Quarter", Duration.ofSeconds(31556952L / 4)),

    YEAR("Years", Duration.ofSeconds(31556952L));

    private final String name;
    private final Duration duration;

    DurationUnit(String name, Duration duration) {
        this.name = name;
        this.duration = duration;
    }

    public Duration getDuration() {
        return duration;
    }

    public String getName() {
        return name;
    }

}

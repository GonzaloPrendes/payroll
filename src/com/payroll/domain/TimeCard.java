package com.payroll.domain;

import com.payroll.infrastructure.TimeCardStream;

import java.time.Duration;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public class TimeCard {

    private final LocalDate date;
    private final Duration hoursWorked;

    private TimeCard(LocalDate date, Duration hoursWorked) {
        this.date = date;
        this.hoursWorked = hoursWorked;
    }

    public static TimeCard of(LocalDate date, Duration hoursWorked) {
        return new TimeCard(date, hoursWorked);
    }

    public static TimeCardStream stream(List<TimeCard> timeCards) {
        return new TimeCardStream(timeCards.stream());
    }

    public Optional<TimeCard> isInPayPeriod(LocalDate payDate, PaymentScheduler scheduler) {
        return scheduler.isDateInBetween(this.date, payDate)
                ? Optional.of(this)
                : Optional.empty();
    }

    public Duration getHours() {
        return this.hoursWorked;
    }
}

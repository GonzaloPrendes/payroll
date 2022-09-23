package com.payroll.domain.scheduler;

import com.payroll.domain.PaymentScheduler;

import java.time.DayOfWeek;
import java.time.LocalDate;

public class WeeklyScheduler implements PaymentScheduler {

    @Override
    public boolean isPayDay(LocalDate date) {
        return this.isLastDayOfWeek(date);
    }

    @Override
    public boolean isDateInBetween(LocalDate date, LocalDate payDate) {
        return date.isAfter(payDate.minusDays(7)) && date.isBefore(payDate.plusDays(1));
    }

    private boolean isLastDayOfWeek(LocalDate payDate) {
        return payDate.getDayOfWeek().equals(DayOfWeek.FRIDAY);
    }
}

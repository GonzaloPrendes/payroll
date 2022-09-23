package com.payroll.domain.scheduler;

import com.payroll.domain.PaymentScheduler;

import java.time.LocalDate;

public class BiWeeklyScheduler implements PaymentScheduler {

    public static final LocalDate REFERENCE_DATE = LocalDate.of(2022, 9, 9);

    @Override
    public boolean isDateInBetween(LocalDate date, LocalDate payDate) {
        return date.isAfter(payDate.minusDays(14)) && date.isBefore(payDate.plusDays(1));
    }

    @Override
    public boolean isPayDay(LocalDate date) {
        return this.isBiweekly(date);
    }

    private boolean isBiweekly(LocalDate date) {
        return date.getDayOfWeek().equals(REFERENCE_DATE.getDayOfWeek())
                && (this.isOnEvenWeek(date) == this.isOnEvenWeek(REFERENCE_DATE));
    }

    private boolean isOnEvenWeek(LocalDate date) {
        return (date.toEpochDay() / 7) % 2 == 0;
    }
}

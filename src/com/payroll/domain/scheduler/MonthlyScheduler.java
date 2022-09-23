package com.payroll.domain.scheduler;

import com.payroll.domain.PaymentScheduler;

import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;

public class MonthlyScheduler implements PaymentScheduler {

    @Override
    public boolean isDateInBetween(LocalDate date, LocalDate payDate) {
        return true;
    }

    @Override
    public boolean isPayDay(LocalDate date) {
        return this.isLastDayOfMonth(date);
    }

    private boolean isLastDayOfMonth(LocalDate date) {
        return date.with(TemporalAdjusters.lastDayOfMonth()).equals(date);
    }
}

package com.payroll.domain;

import com.payroll.common.Money;
import com.payroll.common.MoneyRate;

import java.time.DayOfWeek;
import java.time.Duration;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.stream.Stream;

public class Union {

    private final MoneyRate dueRate;

    public Union(MoneyRate dueRate) {
        this.dueRate = dueRate;
    }

    public Money getDeductions(LocalDate payDate, PaymentScheduler scheduler) {
        return this.dueRate.getTotalFor(this.getDues(payDate, scheduler));
    }

    private Duration getDues(LocalDate payDate, PaymentScheduler scheduler) {
        return Duration.ofDays(this.getWeeklyDues(payDate, scheduler));
    }

    private long getWeeklyDues(LocalDate payDate, PaymentScheduler scheduler) {
        return Stream.iterate(payDate.with(TemporalAdjusters.firstInMonth(payDate.getDayOfWeek())), date -> date.plusDays(1))
                .filter(date -> date.getDayOfWeek().equals(DayOfWeek.FRIDAY) && scheduler.isDateInBetween(date, payDate))
                .takeWhile(date -> date.isBefore(payDate))
                .count();
    }
}

package com.payroll.domain.classification;

import com.payroll.common.Money;
import com.payroll.common.MoneyRate;
import com.payroll.domain.*;
import com.payroll.infrastructure.TimeCardStream;

import java.time.Duration;
import java.time.LocalDate;
import java.util.List;

public class HourlyClassification implements PaymentClassification {

    public static final Duration OVERTIME_RATE = Duration.ofSeconds(5400);
    private final List<TimeCard> timeCards;
    private final MoneyRate rate;

    public HourlyClassification(List<TimeCard> timeCards, MoneyRate rate) {
        this.timeCards = timeCards;
        this.rate = rate;
    }

    @Override
    public Money estimateCompensation(LocalDate payDate, PaymentScheduler scheduler) {
        return this.getFlatSalary(payDate, scheduler).plus(this.getOvertime(payDate, scheduler));
    }

    private Money getFlatSalary(LocalDate payDate, PaymentScheduler scheduler) {
        return this.rate.getTotalFor(this.getNormalHoursWorked(payDate, scheduler));
    }

    @Override
    public String toString() {
        return String.format("Hourly");
    }

    private Duration getNormalHoursWorked(LocalDate payDate, PaymentScheduler scheduler) {
        return this.getTotalHours(payDate, scheduler).minus(this.getExtraHours(payDate, scheduler));
    }

    private Money getOvertime(LocalDate payDate, PaymentScheduler scheduler) {
        return this.rate.getTotalFor(OVERTIME_RATE).scale(this.getExtraHours(payDate, scheduler).toHours());
    }

    private Duration getTotalHours(LocalDate payDate, PaymentScheduler scheduler) {
        return this.timeCards().totalHours(payDate, scheduler);
    }

    private Duration getExtraHours(LocalDate payDate, PaymentScheduler scheduler) {
        return this.timeCards().extraHours(payDate, scheduler);
    }

    private TimeCardStream timeCards() {
        return TimeCard.stream(this.timeCards);
    }
}

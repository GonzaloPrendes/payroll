package com.payroll.infrastructure;

import com.payroll.domain.PaymentScheduler;
import com.payroll.domain.TimeCard;

import java.time.Duration;
import java.time.LocalDate;
import java.util.Optional;
import java.util.stream.Stream;

public class TimeCardStream implements ForwardingStream<TimeCard> {

    private Stream<TimeCard> stream;

    public TimeCardStream(Stream<TimeCard> stream) {
        this.stream = stream;
    }

    @Override
    public Stream<TimeCard> getStream() {
        return this.stream;
    }


    public Duration totalHours(LocalDate date, PaymentScheduler scheduler) {
        return this.timeCardsInBetween(date, scheduler)
                .map(TimeCard::getHours)
                .reduce(Duration::plus)
                .orElse(Duration.ZERO);
    }

    public Duration extraHours(LocalDate date, PaymentScheduler scheduler) {
        return this.timeCardsInBetween(date, scheduler)
                .map(TimeCard::getHours)
                .map(hours -> hours.minus(Duration.ofHours(8)))
                .filter(hours -> !hours.isNegative())
                .reduce(Duration::plus)
                .orElse(Duration.ZERO);
    }

    private Stream<TimeCard> timeCardsInBetween(LocalDate date, PaymentScheduler scheduler) {
        return this.stream.map(tc -> tc.isInPayPeriod(date, scheduler))
                .filter(Optional::isPresent)
                .map(Optional::get);
    }
}

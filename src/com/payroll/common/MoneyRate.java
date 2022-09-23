package com.payroll.common;

import java.time.Duration;

public class MoneyRate {

    private final Money intervalAmount;
    private final Duration interval;

    private MoneyRate(Money amount, Duration duration) {
        this.intervalAmount = amount;
        this.interval = duration;
    }

    public static MoneyRate hourly(Money amount) {
        return new MoneyRate(amount, Duration.ofHours(1));
    }


    public Money getTotalFor(Duration interval) {
        return this.intervalAmount.scale(interval.getSeconds(), this.getSeconds());
    }

    private long getSeconds() {
        return this.interval.getSeconds();
    }

    @Override
    public String toString() {
        return String.format("%s by %s", this.intervalAmount, this.interval.toHours() + " hours");
    }

}

package com.payroll.infrastructure;

import com.payroll.common.Money;

import java.util.stream.Stream;

public class MoneyStream implements ForwardingStream<Money> {

    private final Stream<Money> stream;

    public MoneyStream(Stream<Money> stream) {
        this.stream = stream;
    }

    @Override
    public Stream<Money> getStream() {
        return this.stream;
    }

    public Money sum() {
        return this.stream.reduce(Money::plus).orElse(Money.ZERO);
    }
}

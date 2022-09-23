package com.payroll.common;

import com.payroll.infrastructure.MoneyStream;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.stream.Stream;

public class Money {

    public static final Money ONE = new Money(BigDecimal.ONE);
    public static final Money ZERO = new Money(BigDecimal.ZERO);
    private final BigDecimal amount;

    private Money(BigDecimal amount) {
        this.amount = amount.setScale(2, RoundingMode.HALF_UP);
    }

    public static Money of(double amount) {
        return new Money(new BigDecimal(amount));
    }

    public static MoneyStream stream(Stream<Money> stream) {
        return new MoneyStream(stream);
    }

    public Money plus(Money other) {
        return new Money(this.amount.add(other.amount));
    }

    public Money substract(Money other) {
        return new Money(this.amount.subtract(other.amount));
    }

    public Money scale(long multiply, long divide) {
        return this.scale(new BigDecimal(multiply), new BigDecimal(divide));
    }

    public Money scale(double factor) {
        BigDecimal newAmount = this.amount.multiply(new BigDecimal(factor));
        return new Money(newAmount);
    }

    private Money scale(BigDecimal multiply, BigDecimal divide) {
        return new Money(this.amount.multiply(multiply).divide(divide, 2, RoundingMode.HALF_UP));
    }

    @Override
    public String toString() {
        return "$" + this.amount;
    }

}

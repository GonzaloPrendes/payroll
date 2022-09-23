package com.payroll.infrastructure;

import com.payroll.domain.PayCheck;

import java.util.stream.Stream;

public class PaycheckStream implements ForwardingStream<PayCheck> {

    private final Stream<PayCheck> stream;

    public PaycheckStream(Stream<PayCheck> stream) {
        this.stream = stream;
    }

    @Override
    public Stream<PayCheck> getStream() {
        return this.stream;
    }

    public void deliverPayCheck() {
        this.stream.forEach(System.out::println);
    }
}

package com.payroll.infrastructure;

import com.payroll.domain.Employee;

import java.time.LocalDate;
import java.util.Optional;
import java.util.stream.Stream;

public class EmployeeStream implements ForwardingStream<Employee> {

    private final Stream<Employee> stream;

    public EmployeeStream(Stream<Employee> stream) {
        this.stream = stream;
    }

    @Override
    public Stream<Employee> getStream() {
        return this.stream;
    }

    public EmployeeStream withPayDay(LocalDate today) {
        return new EmployeeStream(
                this.stream.map(employee -> employee.isPayDay(today))
                        .filter(Optional::isPresent)
                        .map(Optional::get)
        );
    }

    public PaycheckStream pay(LocalDate date) {
        return new PaycheckStream(this.stream.map(employee -> employee.compensation(date)));
    }
}

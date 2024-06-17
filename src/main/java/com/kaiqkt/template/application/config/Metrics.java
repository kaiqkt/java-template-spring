package com.kaiqkt.template.application.config;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.DistributionSummary;
import io.micrometer.core.instrument.Gauge;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Timer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.function.Supplier;

/**
 * Metrics class for managing various types of metrics.
 */
@Component
public class Metrics {
    private final MeterRegistry meterRegistry;

    /**
     * Constructor for Metrics class.
     *
     * @param meterRegistry the meter registry to use for metrics
     */
    @Autowired
    public Metrics(MeterRegistry meterRegistry) {
        this.meterRegistry = meterRegistry;
    }

    /**
     * Creates and increments a Counter metric.
     * Counter is a simple incrementing and decrementing metric.
     *
     * @param name the name of the counter
     * @param tags the tags of the counter
     */
    public void increment(String name, String... tags) {
        Counter counter = Counter.builder(name)
                .tags(tags)
                .register(meterRegistry);
        counter.increment();
    }

    /**
     * Creates a Gauge metric.
     * Gauge is a metric that represents a single numerical value that can arbitrarily go up and down.
     *
     * @param name     the name of the gauge
     * @param supplier the supplier of the gauge value
     * @param tags     the tags of the gauge
     * @return the value of the gauge
     */
    public Number gauge(String name, Supplier<Number> supplier, String... tags) {
        return Gauge.builder(name, supplier)
                .tags(tags)
                .register(meterRegistry)
                .value();
    }

    /**
     * Creates a Timer metric and records the time it takes to execute the provided Supplier.
     * Timer is a metric that measures short durations of time.
     *
     * @param <T>      the type of the return value
     * @param name     the name of the timer
     * @param supplier the supplier of the timer value
     * @param tags     the tags of the timer
     * @return the value of the timer
     */
    public <T> T timer(String name, Supplier<T> supplier, String... tags) {
        Timer timer = Timer.builder(name).tags(tags).register(meterRegistry);
        Timer.Sample sample = Timer.start(meterRegistry);
        try {
            return supplier.get();
        } finally {
            sample.stop(timer);
        }
    }

    /**
     * Creates a Timer metric and records the time it takes to execute the provided Runnable.
     *
     * @param name     the name of the timer
     * @param runnable the runnable to time
     * @param tags     the tags of the timer
     */
    public void timer(String name, Runnable runnable, String... tags) {
        Timer timer = Timer.builder(name).tags(tags).register(meterRegistry);
        Timer.Sample sample = Timer.start(meterRegistry);
        try {
            runnable.run();
        } finally {
            sample.stop(timer);
        }
    }

    /**
     * Creates a Histogram metric and records a value.
     * Histogram is a metric that measures the statistical distribution of values in a stream of data.
     *
     * @param name   the name of the histogram
     * @param amount the amount to record in the histogram
     * @param tags   the tags of the histogram
     */
    public void histogram(String name, double amount, String... tags) {
        DistributionSummary summary = DistributionSummary.builder(name)
                .tags(tags)
                .register(meterRegistry);
        summary.record(amount);
    }
}

package com.kaiqkt.template.application.config;

import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.simple.SimpleMeterRegistry;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MetricsConfiguration {
    /**
     * This method creates a Bean of MeterRegistry using the SimpleMeterRegistry implementation.
     * SimpleMeterRegistry is a basic implementation of MeterRegistry that accumulates metrics in memory.
     *
     * @return a new instance of SimpleMeterRegistry
     */
    @Bean
    public MeterRegistry meterRegistry() {
        return new SimpleMeterRegistry();
    }
}

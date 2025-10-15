package com.degaltseva.library.utils;

import org.springframework.boot.actuate.metrics.MetricsEndpoint;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Команда для отображения системных метрик Spring Boot Actuator
 * через консольное управление.
 */
@Component
public class MetricCommand implements Command {

    private final MetricsEndpoint metricsEndpoint;

    public MetricCommand(MetricsEndpoint metricsEndpoint) {
        this.metricsEndpoint = metricsEndpoint;
    }

    @Override
    public boolean supports(String input) {
        return input.startsWith("metric ");
    }

    @Override
    public void execute(String input) {
        String[] parts = input.trim().split(" +");
        if (parts.length < 2) {
            System.err.println("Использование: metric <metric_name>");
            return;
        }

        String metricName = parts[1];
        MetricsEndpoint.MetricDescriptor metricDescriptor = metricsEndpoint.metric(metricName, null);

        if (metricDescriptor == null) {
            System.err.printf("Метрика '%s' не найдена.%n", metricName);
            return;
        }

        System.out.printf("Метрика: %s%n", metricName);
        System.out.println("--------------------------------");

        if (metricDescriptor.getMeasurements() != null && !metricDescriptor.getMeasurements().isEmpty())
            metricDescriptor.getMeasurements().forEach(m -> System.out.printf("• %s = %.2f%n", m.getStatistic().name(), m.getValue()));
        else
            System.out.println("Нет измерений для этой метрики");

        if (metricDescriptor.getAvailableTags() != null && !metricDescriptor.getAvailableTags().isEmpty())
            metricDescriptor.getAvailableTags().forEach(tag -> System.out.printf("• %s = %s%n", tag.getTag(), tag.getValues()));

        System.out.println("--------------------------------");
    }

    @Override
    public String getDescription() {
        printAvailableMetrics();
        return "metric <metric_name> — показать значение системной метрики (например: metric jvm.memory.used)\n";
    }

    private void printAvailableMetrics() {
        System.out.println("Доступные метрики:");
        Set<String> metricNamesSet = metricsEndpoint.listNames().getNames();
        List<String> metricNames = new ArrayList<>(metricNamesSet);
        metricNames.forEach(name -> System.out.println("• " + name));
    }
}

package com.degaltseva.library.utils;

import org.springframework.boot.actuate.metrics.MetricsEndpoint;
import org.springframework.stereotype.Component;

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
            System.out.println("Доступные метрики можно посмотреть в /actuator/metrics");
            return;
        }

        System.out.printf("Метрика: %s%n", metricName);
        System.out.println("--------------------------------");

        if (metricDescriptor.getMeasurements() != null && !metricDescriptor.getMeasurements().isEmpty()) {
            metricDescriptor.getMeasurements().forEach(m -> {
                System.out.printf("• %s = %.2f%n", m.getStatistic().name(), m.getValue());
            });
        } else {
            System.out.println("Нет измерений для этой метрики");
        }

        if (metricDescriptor.getAvailableTags() != null && !metricDescriptor.getAvailableTags().isEmpty()) {
            System.out.println("\nДоступные теги:");
            metricDescriptor.getAvailableTags().forEach(tag -> {
                System.out.printf("• %s = %s%n", tag.getTag(), tag.getValues());
            });
        }
        System.out.println("--------------------------------");
    }

    @Override
    public String getDescription() {
        return "metric <metric_name> — показать значение системной метрики (например: metric jvm.memory.used)";
    }
}

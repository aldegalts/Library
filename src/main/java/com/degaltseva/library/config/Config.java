package com.degaltseva.library.config;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.degaltseva.library.entity.Book;
import com.degaltseva.library.entity.User;
import com.degaltseva.library.utils.CommandProcessor;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class Config {

    @Value("${spring.application.name}")
    private String appName;

    @Value("${spring.application.version}")
    private String appVersion;

    @Bean
    @Scope(value = BeanDefinition.SCOPE_SINGLETON)
    public List<User> userContainer() {
        return new ArrayList<>();
    }

    @Bean
    @Scope(value = BeanDefinition.SCOPE_SINGLETON)
    public List<Book> bookContainer() {
        return new ArrayList<>();
    }

    @Bean
    public CommandLineRunner commandScanner(CommandProcessor commandProcessor) {
        return args -> {
            try (Scanner scanner = new Scanner(System.in)) {
                System.out.println("Введите команду. 'exit' для выхода.");
                while (true) {
                    System.out.print("> ");
                    String input = scanner.nextLine();
                    if ("exit".equalsIgnoreCase(input.trim())) {
                        System.out.println("Выход из программы...");
                        break;
                    }
                    commandProcessor.processCommand(input);
                }
            }
        };
    }

    @PostConstruct
    public void printAppInfo() {
        System.out.println("=====================================");
        System.out.printf("Приложение: %s%n", appName);
        System.out.printf("Версия: %s%n", appVersion);
        System.out.println("=====================================");
    }
}

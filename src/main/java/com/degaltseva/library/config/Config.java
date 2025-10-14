package com.degaltseva.library.config;

import java.util.ArrayList;
import java.util.List;

import com.degaltseva.library.entity.Book;
import com.degaltseva.library.entity.User;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class Config {

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
}

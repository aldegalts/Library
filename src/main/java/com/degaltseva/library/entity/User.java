package com.degaltseva.library.entity;

/**
 * Сущность пользователя библиотеки.
 *
 * Возможности: хранение ID и логина пользователя.
 */
public class User {

    private Long id;
    private String login;

    public User() {

    }

    public User(Long id, String login) {
        this.id = id;
        this.login = login;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }
}

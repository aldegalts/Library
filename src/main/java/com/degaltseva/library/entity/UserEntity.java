package com.degaltseva.library.entity;

import jakarta.persistence.*;
import com.degaltseva.library.entity.enums.Role;
import java.util.List;

/**
 * Сущность пользователя библиотеки.
 * <p>
 * Возможности: хранение ID, имени, фамилии, почты и номера телефона пользователя.
 */
@Entity
@Table(name = "Users")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long userId;

    @Column(name = "user_first_name", length = 100, nullable = false)
    private String userFirstName;

    @Column(name = "user_last_name", length = 100, nullable = false)
    private String userLastName;

    @Column(name = "user_email", length = 255, nullable = false, unique = true)
    private String userEmail;

    @Column(name = "phone", length = 20, nullable = false, unique = true)
    private String phone;

    @Column(name = "password_hash", nullable = false)
    private String password_hash;

    @Column(name = "role", nullable = false)
    private Role role;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<ReservationEntity> reservations;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<LoanEntity> loans;

    public UserEntity() {

    }

    public UserEntity(String userFirstName, String userLastName, String userEmail, String phone, String password_hash, Role role) {
        this.userFirstName = userFirstName;
        this.userLastName = userLastName;
        this.userEmail = userEmail;
        this.phone = phone;
        this.password_hash = password_hash;
        this.role = role;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserFirstName() {
        return userFirstName;
    }

    public void setUserFirstName(String userFirstName) {
        this.userFirstName = userFirstName;
    }

    public String getUserLastName() {
        return userLastName;
    }

    public void setUserLastName(String userLastName) {
        this.userLastName = userLastName;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword_hash() {
        return password_hash;
    }

    public void setPassword_hash(String password_hash) {
        this.password_hash = password_hash;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public List<ReservationEntity> getReservations() {
        return reservations;
    }

    public List<LoanEntity> getLoans() {
        return loans;
    }
}

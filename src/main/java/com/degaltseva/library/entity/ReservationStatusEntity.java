package com.degaltseva.library.entity;

import jakarta.persistence.*;

import java.util.List;

/**
 * Сущность статус бронирования книги.
 * <p>
 * Возможности: хранение ID и статуса бронирования книги.
 */
@Entity
@Table(name = "Reservation_status")
public class ReservationStatusEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "reservation_status_id")
    private Long reservationStatusId;

    @Column(name = "reservation_status", length = 20, nullable = false, unique = true)
    private String reservationStatus;

    @OneToMany(mappedBy = "status", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<ReservationEntity> reservations;

    public ReservationStatusEntity() {

    }

    public ReservationStatusEntity(String reservationStatus) {
        this.reservationStatus = reservationStatus;
    }

    public Long getReservationStatusId() {
        return reservationStatusId;
    }

    public void setReservationStatusId(Long reservationStatusId) {
        this.reservationStatusId = reservationStatusId;
    }

    public String getReservationStatus() {
        return reservationStatus;
    }

    public void setReservationStatus(String reservationStatus) {
        this.reservationStatus = reservationStatus;
    }

    public List<ReservationEntity> getReservations() {
        return reservations;
    }
}

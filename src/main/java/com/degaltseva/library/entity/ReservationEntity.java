package com.degaltseva.library.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

/**
 * Сущность бронирования книги пользователем.
 * <p>
 * Хранит информацию о том, кто, какую книгу и когда забронировал,
 * срок действия брони и текущий статус.
 */
@Entity
@Table(name = "Reservation")
public class ReservationEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "reservation_id")
    private Long reservationId;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity user;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "book_id", nullable = false)
    private BookEntity book;

    @Column(name = "reservation_date", nullable = false)
    private LocalDateTime reservationDate = LocalDateTime.now();

    @Column(name = "expiration_date", nullable = false)
    private LocalDateTime expirationDate;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "status_id", nullable = false)
    private ReservationStatusEntity status;

    public ReservationEntity() {

    }

    public ReservationEntity(UserEntity user, BookEntity book, LocalDateTime expirationDate) {
        this.user = user;
        this.book = book;
        this.expirationDate = expirationDate;
    }

    public Long getReservationId() {
        return reservationId;
    }

    public void setReservationId(Long reservationId) {
        this.reservationId = reservationId;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public BookEntity getBook() {
        return book;
    }

    public void setBook(BookEntity book) {
        this.book = book;
    }

    public LocalDateTime getReservationDate() {
        return reservationDate;
    }

    public void setReservationDate(LocalDateTime reservationDate) {
        this.reservationDate = reservationDate;
    }

    public LocalDateTime getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(LocalDateTime expirationDate) {
        this.expirationDate = expirationDate;
    }

    public ReservationStatusEntity getStatus() {
        return status;
    }

    public void setStatus(ReservationStatusEntity status) {
        this.status = status;
    }
}

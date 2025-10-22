package com.degaltseva.library.repository;

import com.degaltseva.library.entity.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;
import java.util.UUID;

@SpringBootTest
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private ReservationStatusRepository reservationStatusRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    private final Random random = new Random();

    @BeforeEach
    void cleanDatabase() {
        reservationRepository.deleteAll();
        reservationStatusRepository.deleteAll();
        userRepository.deleteAll();
        categoryRepository.deleteAll();
        bookRepository.deleteAll();
    }

    /**
     * Проверяет, что метод корректно находит пользователей, забронировавших конкретную книгу.
     */
    @Test
    @Transactional
    void testFindUsersByBookId_positive() {
        // Подготовка
        UserEntity user = createAndSaveUser();
        CategoryEntity category = createAndSaveCategory();
        BookEntity book = createAndSaveBook(category, 2020);
        ReservationStatusEntity status = createAndSaveStatus("ACTIVE");

        createAndSaveReservation(user, book, status);

        // Действие
        List<UserEntity> foundUsers = userRepository.findUsersByBookId(book.getBookId());

        // Проверки
        Assertions.assertEquals(1, foundUsers.size());
        Assertions.assertEquals(user.getUserEmail(), foundUsers.get(0).getUserEmail());
    }

    /**
     * Проверяет, что метод возвращает пустой список, если бронирований для книги нет.
     */
    @Test
    @Transactional
    void testFindUsersByBookId_negative() {
        // Подготовка
        CategoryEntity category = createAndSaveCategory();
        BookEntity book = createAndSaveBook(category, 2019);

        // Действие
        List<UserEntity> foundUsers = userRepository.findUsersByBookId(book.getBookId());

        // Проверка
        Assertions.assertTrue(foundUsers.isEmpty());
    }

    private UserEntity createAndSaveUser() {
        UserEntity user = new UserEntity(
                "Name_" + UUID.randomUUID(),
                "Surname_" + UUID.randomUUID(),
                "email_" + UUID.randomUUID() + "@example.com",
                "8" + (1000000000L + random.nextInt(899999999))
        );
        return userRepository.save(user);
    }

    private CategoryEntity createAndSaveCategory() {
        return categoryRepository.save(new CategoryEntity(
                "Category_" + UUID.randomUUID(),
                "Description_" + UUID.randomUUID()
        ));
    }

    private BookEntity createAndSaveBook(CategoryEntity category, int year) {
        return bookRepository.save(new BookEntity(
                "Book_" + UUID.randomUUID(),
                "Author_" + UUID.randomUUID(),
                "Publisher_" + UUID.randomUUID(),
                year,
                category,
                random.nextInt(10) + 1,
                random.nextInt(10) + 1
        ));
    }

    private ReservationStatusEntity createAndSaveStatus(String statusName) {
        ReservationStatusEntity status = new ReservationStatusEntity();
        status.setReservationStatus(statusName);
        return reservationStatusRepository.save(status);
    }

    private ReservationEntity createAndSaveReservation(UserEntity user, BookEntity book, ReservationStatusEntity status) {
        ReservationEntity reservation = new ReservationEntity(user, book, LocalDateTime.now().plusDays(3));
        reservation.setStatus(status);
        return reservationRepository.save(reservation);
    }
}

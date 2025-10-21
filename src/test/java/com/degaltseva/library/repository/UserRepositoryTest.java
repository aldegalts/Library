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

    @BeforeEach
    void cleanDatabase() {
        reservationRepository.deleteAll();
        reservationStatusRepository.deleteAll();
        userRepository.deleteAll();
        categoryRepository.deleteAll();
        bookRepository.deleteAll();
    }

    @Test
    @Transactional
    void testFindUsersByBookId_positive() {
        // given
        UserEntity user = new UserEntity("Name", "Surname", "email@example.com", "81234567890");
        user = userRepository.save(user);
        CategoryEntity category = new CategoryEntity("NonFiction", "NonFiction description");
        category = categoryRepository.save(category);

        BookEntity book = new BookEntity("Other Book", "Author", "Publisher", 2019, category, 2, 2);
        bookRepository.save(book);

        ReservationStatusEntity status = new ReservationStatusEntity();
        status.setReservationStatus("ACTIVE");
        status = reservationStatusRepository.save(status);

        ReservationEntity reservation = new ReservationEntity(user, book, LocalDateTime.now().plusDays(3));
        reservation.setStatus(status);
        reservationRepository.save(reservation);

        // when
        List<UserEntity> foundUsers = userRepository.findUsersByBookId(book.getBookId());

        // then
        Assertions.assertEquals(1, foundUsers.size());
        Assertions.assertEquals(user.getUserEmail(), foundUsers.get(0).getUserEmail());
    }

    @Test
    @Transactional
    void testFindUsersByBookId_negative() {
        // given
        CategoryEntity category = new CategoryEntity("NonFiction", "NonFiction description");
        category = categoryRepository.save(category);

        BookEntity book = new BookEntity("Other Book", "Author", "Publisher", 2019, category, 2, 2);
        bookRepository.save(book);

        // when
        List<UserEntity> foundUsers = userRepository.findUsersByBookId(book.getBookId());

        // then
        Assertions.assertTrue(foundUsers.isEmpty());
    }
}

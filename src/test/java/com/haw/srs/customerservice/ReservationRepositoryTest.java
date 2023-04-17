package com.haw.srs.customerservice;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = Application.class, webEnvironment = SpringBootTest.WebEnvironment.NONE)
@ActiveProfiles(profiles = "testing")
public class ReservationRepositoryTest {

    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @BeforeEach
    void setUp() {
        // to delete reservationRepo, must delete customerRepo before that
        // to avoid org.hibernate.exception.ConstraintViolationException
        this.customerRepository.deleteAll();
        this.reservationRepository.deleteAll();

        Movie movie = new Movie("James Bond 007");
        Reservation reservation = new Reservation(movie);
        reservationRepository.save(reservation);
    }

    @Test
    void getReservationSuccess() {
        Optional<Reservation> reservation = reservationRepository.findByMovie("James Bond 007");
        assertThat(reservation).isPresent();
    }
}

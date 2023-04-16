package com.haw.srs.customerservice;

import org.hibernate.PersistentObjectException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = Application.class, webEnvironment = SpringBootTest.WebEnvironment.NONE)
@ActiveProfiles(profiles = "testing")
class JPAExceptionTest {

    @PersistenceContext
    private EntityManager entityManager;

    @Test
    @Transactional
    void getAllCustomersSuccess() {

        Customer customer = new Customer("Jane", "Doe", Gender.FEMALE,
                "jane.doe@mail.com",null);
        Reservation reservation = new Reservation(new Movie("James Bond 007"));
        customer.addReservation(reservation);
        
        entityManager.persist(customer);
        entityManager.detach(customer);
        assertThatExceptionOfType(RuntimeException.class).isThrownBy(() -> { entityManager.persist(customer); });
    }
}

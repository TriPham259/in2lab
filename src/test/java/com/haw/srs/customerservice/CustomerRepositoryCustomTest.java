package com.haw.srs.customerservice;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = Application.class, webEnvironment = SpringBootTest.WebEnvironment.NONE)
@ActiveProfiles(profiles = "testing")
class CustomerRepositoryCustomTest {

    @Autowired
    private CustomerRepository customerRepository;

    @BeforeEach
    void setUp() {
        this.customerRepository.deleteAll();
    }

    @Test
    void getAllCustomersSuccess() {

        Customer customer = new Customer("Stefan", "Sarstedt", Gender.MALE,
                "stefan.sarstedt@haw-hamburg.de",null);
        customerRepository.save(customer);
        customer = customerRepository.save(new Customer("Jane", "Doe", Gender.FEMALE,
                "jane.doe@mail.com",null));
        Reservation reservation = new Reservation(new Movie("James Bond 007"));
        customer.addReservation(reservation);
        customerRepository.save(customer);

        assertThat(customerRepository.count()).isEqualTo(2);
        List<Customer> actual = customerRepository.findCustomersWithoutReservations();
        assertThat(actual).size().isEqualTo(1);
    }
}

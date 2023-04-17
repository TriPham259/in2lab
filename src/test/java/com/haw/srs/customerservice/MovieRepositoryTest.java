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
public class MovieRepositoryTest {

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @BeforeEach
    void setUp() {
        // to delete movieRepo, must delete customerRepo before that
        // to avoid org.hibernate.exception.ConstraintViolationException
        this.customerRepository.deleteAll();
        this.movieRepository.deleteAll();

        Movie movie = new Movie("James Bond 007", 180);
        movieRepository.save(movie);
    }

    @Test
    void getMovieSuccess() {
        Optional<Movie> movie = movieRepository.findByTitle("James Bond 007");
        assertThat(movie).isPresent();
    }
}

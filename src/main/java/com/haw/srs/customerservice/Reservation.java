package com.haw.srs.customerservice;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name="movieId")
    private Movie movie;

    private int seatNumber;

    private int hallNumber;

    public Reservation(Movie movie) {
        this.movie = movie;
        this.seatNumber = 1;
        this.hallNumber = 1;
    }
}

package com.fr.epita.javabackend.model;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@Data
@Entity
@Table(name = "seen")
public class SeenMovies {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "date")
    private String date;

    @OneToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL,orphanRemoval = true)
    @JoinColumn(name = "movie_id",nullable = false)
    int movie_id;

    @OneToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL,orphanRemoval = true)
    @JoinColumn(name = "user_id",nullable = false)
    int user_id;

    public SeenMovies(Long id, String date, int movie, int user) {
        this.id = id;
        this.date = date;
        this.movie_id = movie;
        this.user_id = user;
    }


}

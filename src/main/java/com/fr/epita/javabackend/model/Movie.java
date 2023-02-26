package com.fr.epita.javabackend.model;

import lombok.*;

import javax.persistence.*;
import java.util.List;


@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@Data
@Entity
@Table(name = "movie")
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "releasedata")
    private String releasedate;

    @Column(name = "rating")
    private double rating;

    @Column(name = "image")
    private String image;

    @Column(name = "moviecategory")
    private String moviecategory;

    @Column(name = "moviedirector")
    private String moviedirector;

    public Movie(Long id,String title, String releaseDate, double rating, String image, String category, String director) {
        this.title = title;
        this.releasedate = releaseDate;
        this.rating = rating;
        this.image = image;
        this.moviecategory = category;
        this.moviedirector = director;
        this.id = id;
    }
}

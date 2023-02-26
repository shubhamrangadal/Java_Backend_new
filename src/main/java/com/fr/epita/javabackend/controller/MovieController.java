package com.fr.epita.javabackend.controller;


import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.fr.epita.javabackend.model.Movie;
import com.fr.epita.javabackend.repo.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
public class MovieController {

    @Autowired
    private MovieRepository movieRepository;

    @GetMapping("/movies")
    public List<Movie> getAllMovies() {
        return movieRepository.getAllMovies();
    }

    @GetMapping("/movies/notseen/{userId}")
    public List<Movie> getMoviesNotSeenByUserId(@PathVariable Long userId){
        return movieRepository.getMoviesNotSeenByUserId(userId);
    }


    @GetMapping("/movies/recommend/{userId}")
    public List<Movie> getMoviesSeenByUserId(@PathVariable Long userId){
        return movieRepository.getRecommendedMoviesByUserId(userId);
    }


    @GetMapping("/movies/{id}")
    public Movie getMovieById(@PathVariable Long id) {
        return movieRepository.getMovieById(id);
    }

    @PostMapping("/movies")
    public void addMovie(@RequestBody Movie movie) {
        movieRepository.addMovie(movie);
    }


}


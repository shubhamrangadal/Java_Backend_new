package com.fr.epita.javabackend.controller;

import com.fr.epita.javabackend.model.Movie;
import com.fr.epita.javabackend.model.SeenMovies;
import com.fr.epita.javabackend.repo.SeenMoviesRespoitory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class SeenMoviesController {

    @Autowired
    private SeenMoviesRespoitory seenMoviesRepository;


    @GetMapping("/seenmovies/{userId}")
    public List<Movie> getSeenMoviesByUserId(@PathVariable Long userId){
        return seenMoviesRepository.getSeenMoviesByUserId(userId);
    }


    @PostMapping("/seenmovies/addseenmovie")
    public void addSeenMovie(@RequestBody SeenMovies seenMovies){
        seenMoviesRepository.createSeenMovie(seenMovies);
    }









}

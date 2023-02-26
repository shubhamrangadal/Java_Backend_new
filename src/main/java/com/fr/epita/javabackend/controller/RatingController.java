package com.fr.epita.javabackend.controller;

import com.fr.epita.javabackend.model.Rating;
import com.fr.epita.javabackend.repo.MovieRepository;
import com.fr.epita.javabackend.repo.RatingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class RatingController {

    @Autowired
    private RatingRepository ratingRepository;


    @GetMapping("/ratings/moviebyid/{movieId}")
    public List<Rating> getRatingByMovieId(@PathVariable Long movieId){
        return ratingRepository.getRatingByMovieId(movieId);
    }


    @PostMapping("/ratings/createrating")
    public void createRating(@RequestBody Rating rating){
        ratingRepository.createRating(rating);
    }


    @GetMapping("/ratings/ratingbyid/{id}")
    public Rating getRatingById(@PathVariable Long id){
        return ratingRepository.getRatingById(id);
    }


}

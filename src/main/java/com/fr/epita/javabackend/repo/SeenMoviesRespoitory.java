package com.fr.epita.javabackend.repo;

import com.fr.epita.javabackend.model.Movie;
import com.fr.epita.javabackend.model.SeenMovies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class SeenMoviesRespoitory {


    @Autowired
    @Qualifier("movieJdbcTemplate")
    private JdbcTemplate seenMoviesJdbcTemplate;


    public void createSeenMovie(SeenMovies seenMovies){
        seenMoviesJdbcTemplate.update("insert into seen (user_id, movie_id, date) values (?, ?, ?)",
                seenMovies.getUser_id(),
                seenMovies.getMovie_id(),
                seenMovies.getDate()
        );
    }



    public List<Movie> getSeenMoviesByUserId(Long userId) {

        List<SeenMovies> movies = seenMoviesJdbcTemplate.query("select * from seen where user_id = ?", new Object[]{userId}, (rs, rowNum) ->
                new SeenMovies(
                        rs.getLong("id"),
                        rs.getString("date"),
                        rs.getInt("movie_id"),
                        rs.getInt("user_id")
                )
        );

        List<Movie> moviesList = new ArrayList<>();

        for (SeenMovies movie : movies) {

            Movie movies1 = seenMoviesJdbcTemplate.queryForObject("select * from movie where id = ?", new Object[]{movie.getMovie_id()}, (rs, rowNum) ->
                    new Movie(rs.getLong("id"),
                            rs.getString("title"),
                            rs.getString("releaseDate"),
                            rs.getDouble("rating"),
                            rs.getString("image"),
                            rs.getString("moviecategory"),
                            rs.getString("moviedirector")));

            moviesList.add(movies1);

        }

        return moviesList;
    }



}

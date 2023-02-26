package com.fr.epita.javabackend.repo;

import com.fr.epita.javabackend.AppConfig;
import com.fr.epita.javabackend.model.Movie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.FluentQuery;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;


@Service
public class MovieRepository {

    @Autowired
    @Qualifier("movieJdbcTemplate")
    private JdbcTemplate movieJdbcTemplate;

    public List<Movie> getAllMovies(){

        return movieJdbcTemplate.query("select * from movie", new RowMapper<Movie>() {
            @Override
            public Movie mapRow(ResultSet rs, int rowNum) throws SQLException {
                return new Movie(
                        rs.getLong("id"),
                        rs.getString("title"),
                        rs.getString("releasedate"),
                        rs.getDouble("rating"),
                        rs.getString("image"),
                        rs.getString("moviecategory"),
                        rs.getString("moviedirector")
                );
            }
        });
    }


    public Movie getMovieById(Long id){
        return movieJdbcTemplate.queryForObject("select * from movie where id = ?", new Object[]{id}, (rs, rowNum) ->
                new Movie(
                        rs.getLong("id"),
                        rs.getString("title"),
                        rs.getString("releasedate"),
                        rs.getDouble("rating"),
                        rs.getString("image"),
                        rs.getString("moviecategory"),
                        rs.getString("moviedirector")
                ));
    }

    public void deleteMovieById(Long id){
        movieJdbcTemplate.update("delete from movie where id = ?", id);
    }

    public void addMovie(Movie movie){
        movieJdbcTemplate.update("insert into movie (title, releasedate, rating) values (?, ?, ?)",
                movie.getTitle(), movie.getReleasedate(), movie.getRating());
    }

    public void updateMovie(Long id, Movie movie){
        movieJdbcTemplate.update("update movie set title = ?, releasedate = ?, rating = ? where id = ?",
                movie.getTitle(), movie.getReleasedate(), movie.getRating(), movie.getId());
    }


    public List<Movie> getMoviesNotSeenByUserId(Long userId){
        return movieJdbcTemplate.query("select * from movie where id not in (select id from seen where id = ?)", new Object[]{userId}, (rs, rowNum) ->
                new Movie(
                        rs.getLong("id"),
                        rs.getString("title"),
                        rs.getString("releasedate"),
                        rs.getDouble("rating"),
                        rs.getString("image"),
                        rs.getString("moviecategory"),
                        rs.getString("moviedirector")
                ));
    }


    public List<Movie> getRecommendedMoviesByUserId(Long userId){
        return movieJdbcTemplate.query("select * from movie where id in (select id from seen where id = ?)", new Object[]{userId}, (rs, rowNum) ->
                new Movie(
                        rs.getLong("id"),
                        rs.getString("title"),
                        rs.getString("releasedate"),
                        rs.getDouble("rating"),
                        rs.getString("image"),
                        rs.getString("moviecategory"),
                        rs.getString("moviedirector")
                ));
    }

}

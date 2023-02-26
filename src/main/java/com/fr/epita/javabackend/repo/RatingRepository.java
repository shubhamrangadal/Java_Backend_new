package com.fr.epita.javabackend.repo;


import com.fr.epita.javabackend.model.Rating;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RatingRepository {

    @Autowired
    @Qualifier("movieJdbcTemplate")
    private JdbcTemplate ratingJdbcTemplate;

    public void createRating(Rating rating){
        ratingJdbcTemplate.update("insert into rating (user_id, movie_id,rating,commenttitle,commentdesc) values (?, ?, ?, ?, ?)",
                rating.getUser_id(),
                rating.getMovie_id(),
                rating.getRating(),
                rating.getCommenttitle(),
                rating.getCommentdesc()
        );
    }

    public Rating getRatingById(Long id){
        return ratingJdbcTemplate.queryForObject("select * from rating where id = ?", new Object[]{id}, (rs, rowNum) ->
                new Rating(
                        rs.getLong("id"),
                        rs.getInt("rating"),
                        rs.getString("commenttitle"),
                        rs.getString("commentdesc"),
                        rs.getInt("user_id"),
                        rs.getInt("movie_id")
                )
        );
    }


    public List<Rating> getRatingByMovieId(Long movieId){
        return ratingJdbcTemplate.query("select * from rating where movie_id = ?", new Object[]{movieId}, (rs, rowNum) ->
                new Rating(
                        rs.getLong("id"),
                        rs.getInt("rating"),
                        rs.getString("commenttitle"),
                        rs.getString("commentdesc"),
                        rs.getInt("user_id"),
                        rs.getInt("movie_id")
                )
        );
    }





}

package com.fr.epita.javabackend.model;

import lombok.*;

import javax.persistence.*;

@Table(name = "rating_db")
@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@Data
@Entity
public class Rating {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "rating")
    private int rating;

    @Column(name = "commenttitle")
    private String commenttitle;

    @Column(name = "commentdesc")
    private String commentdesc;

    @OneToOne(fetch = FetchType.LAZY,optional = false)
    @JoinColumn(name = "user_id",nullable = false)
    private int user_id;

    @OneToOne(fetch = FetchType.LAZY,optional = false)
    @JoinColumn(name = "movie_id",nullable = false)
    private int movie_id;


    public Rating(Long id, int rating, String commentTitle, String commentContent, int userId, int movieId) {
        this.id = id;
        this.rating = rating;
        this.commenttitle = commentTitle;
        this.commentdesc = commentContent;
        this.user_id = userId;
        this.movie_id = movieId;
    }
}

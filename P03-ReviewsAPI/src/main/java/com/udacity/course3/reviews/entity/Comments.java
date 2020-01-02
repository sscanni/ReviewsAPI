package com.udacity.course3.reviews.entity;

import javax.persistence.*;

@Entity
@Table(name = "comments", catalog = "reviewsdb")
public class Comments {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "comment")
    private String comment;

    @Column(name = "reviewid")
    private Integer reviewid;

//    @OneToOne
//    @MapsId
//    private Reviews reviews;

    public Comments() {
    }

    public Integer getId() { return id; }

    public void setId(Integer id) { this.id = id; }

    public String getComment() { return comment; }

    public void setComment(String comment) { this.comment = comment; }

    public Integer getReviewid() { return reviewid; }

    public void setReviewid(Integer reviewid) { this.reviewid = reviewid; }

//    public Reviews getReviews() {
//        return reviews;
//    }
//
//    public void setReviews(Reviews reviews) {
//        this.reviews = reviews;
//    }
}
package com.udacity.course3.reviews.entity;

import javax.persistence.*;

@Entity
@Table(name = "reviews")
public class Reviews {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "reviewid")
    private Integer reviewid;

    @Column(name = "prodid")
    private Integer prodid;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "reviewid", referencedColumnName = "reviewid")
    private Comments comments;

    public Comments getComments() { return comments; }

    public void setComments(Comments comments) { this.comments = comments; }

    public Reviews () { }

    public Integer getReviewid() {
        return reviewid;
    }

    public void setReviewid(Integer reviewid) {
        this.reviewid = reviewid;
    }

    public Integer getProdid() { return prodid; }

    public void setProdid(Integer prodid) { this.prodid = prodid; }

}
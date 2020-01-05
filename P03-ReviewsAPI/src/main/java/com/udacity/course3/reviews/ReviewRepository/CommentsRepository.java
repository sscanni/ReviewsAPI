package com.udacity.course3.reviews.ReviewRepository;

import com.udacity.course3.reviews.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CommentsRepository extends JpaRepository<Comment, Integer> {

    @Query("select c from Comment c where reviewid=:reviewId")
    public List<Comment> getCommentsbyReviewId(int reviewId);
}
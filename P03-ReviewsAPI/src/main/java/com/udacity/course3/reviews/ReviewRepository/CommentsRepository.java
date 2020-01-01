package com.udacity.course3.reviews.ReviewRepository;

import com.udacity.course3.reviews.entity.Comments;
import com.udacity.course3.reviews.entity.Reviews;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CommentsRepository extends JpaRepository<Comments, Integer> {

    @Query("select c from Comments c where reviewid=:reviewId")
    public List<Comments> getCommentsbyReviewId(int reviewId);
}